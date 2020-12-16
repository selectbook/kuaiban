<template>
	<div class="shopping-cart-wrap">
		<h3 class="shopping-cart-tit">我的购物车<small>共1门课程</small></h3>
		<div class="row">
			 <el-table
			     v-loading="loading"
			    ref="multipleTable"
			    :data="shopCartList"
			    tooltip-effect="dark"
			    style="width: 100%"
			    @selection-change="handleSelectionChange">
			    <el-table-column
			      type="selection"
			      width="55">
			    </el-table-column>
			    <el-table-column
			      label="课程"
			      width="555" >
			      <template slot-scope="scope">
			      	<img :src="scope.row.course_img" alt="">
			      	<a href="javascript:void(0);">{{ scope.row.name}}</a>
			      </template>
			    </el-table-column>
			    <el-table-column
			      prop="name"
			      label="有效期"
			      width="212">
			      <template slot-scope='scope'>
              <!--{{scope.row.relate_price_policy}}-->
			      	<!-- 默认选中 select 中v-model的值 等于 option中value -->
			      	<select v-model = 'scope.row.default_price_period'>
			      		<option v-for = '(item,index) in scope.row.relate_price_policy' :key = 'index'  :value = 'item.valid_period'   >
                  有效期{{item.valid_period_text}}

			      		</option>

			      	</select>
			      </template>

			    </el-table-column>
			    <el-table-column
			      prop="address"
			      label="单价"
			      show-overflow-tooltip>
			      <template slot-scope='scope'>
			      	¥{{scope.row.default_price}}
			      </template>
			    </el-table-column>
			 <el-table-column
			      fixed="right"
			      label="操作"
			      width="120">
			      <template slot-scope="scope">
			        <el-button
			          @click.native.prevent="deleteRow(scope.$index, shopCartList)"
			          type="text"
			          size="small">
			          删除
			        </el-button>
			      </template>
			    </el-table-column>
			  </el-table>
		</div>
		<div class="total">
			<el-button type="primary" @click="account">去结算</el-button>
			<h3>总计: ¥{{totalPrice}}</h3>
		</div>
	</div>
</template>

<script>
export default {
	name:'Cart',
	data(){
		return {
			 multipleSelection: [], //存放选中的当前数据
			 shopCartList:[],
			 currentVal:'',
			 loading: true
		}
	},
	computed:{
		totalPrice(){
			let total = 0;
			this.multipleSelection.forEach((item,index)=>{
				total += parseInt(item.default_price)
			})
			return  total;
		}
	},
	methods:{
    account(){
      if (this.multipleSelection.length){
         let arr = []
        this.multipleSelection.forEach((item,index)=>{
          arr.push({
          "course_id":item.id,
          "price_policy_id":item.default_price_policy_id
        })
      })
//

       this.$http.account(arr)
          .then(res=>{
          console.log(res)
         this.$router.push({
            name:'account'
         })

        })
        .catch(err=>{

        })
      }else{
        this.$message('请选择要购买的课程')
      }


    },
		// 删除课程
		deleteRow(index,rows){
			this.$confirm('你确定删除该课程吗?', '提示', {
		          confirmButtonText: '确定',
		          cancelButtonText: '取消',
		          type: 'warning',
		          center: true
		        }).then(() => {
		        	let params = {
		        		course_id:rows[index].id
		        	}

		        	this.$http.removeCourse(params)
		        	.then(res=>{
                if(res.data.code===1000){

                  this.$message({
                    type: 'success',
                    message: '删除成功!'
                  });

                   rows.splice(index, 1);
                }

		        	})
		        	.catch(err=>{
		        	})


		        }).catch(() => {
		          this.$message({
		            type: 'info',
		            message: '已取消删除'
		          });
		        });


		},
		getShopCartList(){
			this.$http.shoppingcar()
			.then(res=>{
				if (res.code ===1000) {
					this.shopCartList = res.data.shopping_car_list;
          console.log(this.shopCartList)
				}
			})
			.catch(err=>{
			})
		},
		handleSelectionChange(val) {
      this.multipleSelection = val;
		}
	},
  mounted(){
    setTimeout(()=>{
        this.loading = false
    },800)
  },
	created(){
     this.getShopCartList();


	}

};
</script>

<style lang="css" scoped>
.shopping-cart-wrap{
	width: 100%;
}
.shopping-cart-wrap h3,.row{
	width: 1200px;
	margin: 0 auto;
}
.shopping-cart-wrap h3{
	padding: 50px 0 ;
}
.el-table .warning-row {
	background: #22C8C5;
}
.cell img{
	vertical-align: middle;
	width: 170px;
}
.cell a{
	color: #000;
	margin-left: 30px;
}
select {
    border: 0;
    outline: none;
    font-size: 12px;
    color: #666;
    line-height: 18px;
    width: 117px;
    height: 28px;
    padding-left: 16px;
    border: 1px solid #d9d9d9;
    border-radius: 4px;
}
.total{
	width: 1200px;
	margin: 0 auto;
	/*display: flex;*/
	/*justify-content:flex-end;*/

}
.shopping-cart-wrap .total button{
	float: right;
	margin-top: 20px;
}

.shopping-cart-wrap .total h3{
	padding: 0;
	float: right;
	width: 100px;
	height: 30px;
	margin-top: 30px;
}

</style>
