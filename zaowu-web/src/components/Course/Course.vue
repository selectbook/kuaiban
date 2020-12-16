<template>
	<div class="course">
		<div class="container clearfix">
			<ul class="coursebox">
			  <li v-for = '(category,index) in categoryList' :key = "category.id" :class = '{active:index===currentIndex}' @click = 'categoryClick(index,category.id)' >
			  	{{category.name}}
			  </li>
			</ul>

			<div class="courseList" >
				<div class="detail"  v-for = '(course,index) in courseDetail' :key = 'course.id' @click = 'detailHandler(course.coursedetail_id)'>
					<div class="head">
						<img :src="course.course_img" alt="" class="backImg">
						<b class="mask" :style = '{background:course.bgColor}'></b>
						<p>{{course.name}}</p>
					</div>
					<div class="content">
						<p>{{course.brief}}</p>
						<div class="content-detail">
							<div>
								<img src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHN2ZyB3aWR0aD0iMTFweCIgaGVpZ2h0PSIxMnB4IiB2aWV3Qm94PSIwIDAgMTEgMTIiIHZlcnNpb249IjEuMSIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB4bWxuczp4bGluaz0iaHR0cDovL3d3dy53My5vcmcvMTk5OS94bGluayI+CiAgICA8IS0tIEdlbmVyYXRvcjogU2tldGNoIDQ4LjIgKDQ3MzI3KSAtIGh0dHA6Ly93d3cuYm9oZW1pYW5jb2RpbmcuY29tL3NrZXRjaCAtLT4KICAgIDx0aXRsZT5TaGFwZTwvdGl0bGU+CiAgICA8ZGVzYz5DcmVhdGVkIHdpdGggU2tldGNoLjwvZGVzYz4KICAgIDxkZWZzPjwvZGVmcz4KICAgIDxnIGlkPSJSZWN0YW5nbGUtMiIgc3Ryb2tlPSJub25lIiBzdHJva2Utd2lkdGg9IjEiIGZpbGw9Im5vbmUiIGZpbGwtcnVsZT0iZXZlbm9kZCIgdHJhbnNmb3JtPSJ0cmFuc2xhdGUoLTE3LjAwMDAwMCwgLTkxLjAwMDAwMCkiPgogICAgICAgIDxnIGlkPSLkuKrkurrkuK3lv4MiIHRyYW5zZm9ybT0idHJhbnNsYXRlKDE3LjAwMDAwMCwgOTEuMDAwMDAwKSIgZmlsbD0iIzlCOUI5QiIgZmlsbC1ydWxlPSJub256ZXJvIj4KICAgICAgICAgICAgPHBhdGggZD0iTTYuNzU4MjMwODEsNi42MjU2MzEwOCBMNC4yNDEzMzM5Miw2LjYyNTYzMTA4IEMxLjkwMjMxNTc1LDYuNjI1NjMxMDggMCw4LjQxNDM0Nzg0IDAsMTAuNjEzNjY4OSBMMCwxMC44NTAyOTE3IEMwLDEyIDEuODcxODkwNjUsMTIgNC4yNDEzMzM5MiwxMiBMNi43NTgyNDUzMiwxMiBDOS4wMzQ0NTQ2MSwxMiAxMSwxMiAxMSwxMC44NTAyOTE3IEwxMSwxMC42MTM2Njg5IEMxMSw4LjQxNDc3MDM2IDkuMDk3MTkwOTUsNi42MjU2MzEwOCA2Ljc1ODIzMDgxLDYuNjI1NjMxMDggWiBNNS4zNzQwNDEyNiw2LjMyMTI0MjAyIEM3LjIyNjIxNDM2LDYuMzIxMjQyMDIgOC43MzMxOTI0Nyw0LjkwMzU2Mjg4IDguNzMzMTkyNDcsMy4xNjA2Mjc4MiBDOC43MzMxOTI0NywxLjQxNzc3NDU0IDcuMjI2MjE0MzYsMCA1LjM3NDA0MTI2LDAgQzMuNTIxOTI2MiwwIDIuMDE0ODAzLDEuNDE4MDc0MzkgMi4wMTQ4MDMsMy4xNjA2OTU5NyBDMi4wMTQ4MDMsNC45MDMxODEyNSAzLjUyMTkyNjIsNi4zMjEyNDIwMiA1LjM3NDA0MTI2LDYuMzIxMjQyMDIgWiIgaWQ9IlNoYXBlIj48L3BhdGg+CiAgICAgICAgPC9nPgogICAgPC9nPgo8L3N2Zz4K" alt="">
							         <span>{{course.people_but}}</span>
							         <span>{{course.level}}</span>
							      <span class="span3" v-if = 'course.is_free'>
							         	  <span class="s">{{course.origin_price}}</span>
							            <span  class="t">免费</span>
							      </span>
							      <span class="span4" v-else>{{course.price}}</span>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>


	</div>
</template>

<script>
export default {
  name: 'Course',
  data(){
  	return {

  		categoryList:[{
  		  id:1,
        name: '全部',
      },{
        id:2,
        name: 'java',
      },{
        id:3,
        name: 'python',
      },{
        id:4,
        name: 'web前端',
      },{
        id:5,
        name: 'go语言',
      }
      ],// 分类列表
  		currentIndex:0,//分类列表选中
  		categoryId:0,//获取所有的课程列表的id
  		courseDetail:[
        {
          id:1,
          coursedetail_id:1,
          name: 'python21天',
          course_img: '../../../static/images/mac1.jpeg',
          brief:'Python是一种跨平台的计算机程序设计语言。',
          bgColor:'#56CBC4',
          level:'初级',
          people_but:'',
          is_free:true,
          origin_price:'¥200',
          price:'¥0',
        },{
          id:2,
          coursedetail_id:2,
          name: 'java21天',
          course_img: '../../../static/images/mac2.jpeg',
          brief:'Java是一种跨平台的计算机程序设计语言。',
          bgColor:'#4169E1',
          level:'初级',
          people_but:'',
          is_free:true,
          origin_price:'¥100',
          price:'¥0',
        },{
          id:3,
          coursedetail_id:3,
          name: 'go21天',
          course_img: '../../../static/images/mac3.jpg',
          brief:'go是一种跨平台的计算机程序设计语言。',
          bgColor:'#9370DB',
          level:'中级',
          people_but:'',
          is_free:false,
          origin_price:'¥200',
          price:'¥100',
        },{
          id:4,
          coursedetail_id:4,
          name: 'linux21天',
          course_img: '../../../static/images/mac3.jpg',
          brief:'linux简介。',
          bgColor:'#9370DB',
          level:'中级',
          people_but:'',
          is_free:false,
          origin_price:'¥200',
          price:'¥100',
        }
      ],//课程列表详情数据
  		bgColors:['#4AB2BF','#1895C6','#4C87E0','#A361D9','#F7AE6A','#FF14A0','#61F0E1','#6282A7','#27998E','#3C74CC','#A463DA','#F0A257','#DD4B7A','#59C6BD','#617FA1','#1B92C3','#30A297','#3B73CB','#9E57CA','#A463DA','#1895C6','#A361D9','#FF14A0']
  	}
  },
  methods:{
  	// 课程详情 事件操作
  	detailHandler(id){
  		this.$router.push({
  			name:"course.detail",
  			params:{
  				detailId:id
  			}
  		})
  	},
  	categoryClick(index,id){

  		this.currentIndex = index;//修改分类列表的样式
  		this.categoryId = id;
  		this.courseList()
  	},
  	// 获取分类列表
  	getCategoryList(){
  		this.$http.categoryList()
	  	.then(res=>{
	  		// console.log(res);

	  		if (!res.error_no) {
	  			this.categoryList = res.data;
	  			let category = {
	  				id:0,
	  				name:'全部'
	  			}
	  			this.categoryList.unshift(category);


	  		}
	  	})
	  	.catch(err=>{
	  		console.log(err);
	  	});
  	},
  	// 获取全部的课程列表
  	courseList(){

  		console.log(this.categoryId);
  		this.$http.courseList(this.categoryId)
	  	.then(res=>{
	  		if (!res.code) {
	  			this.courseDetail = res.data;
	  			console.log(this.courseDetail)
	  			this.courseDetail.forEach((item,index)=>{
	  				this.bgColors.forEach((bgColor,i)=>{
	  					if (i===index) {
	  						item.bgColor = bgColor;
	  					}
	  				})
	  			})
	  		}
	  	})
	  	.catch(err=>{
	  	})
  	}
  },
  // 生命周期  在created方法发起ajax请求
  created(){
  	// 返回一个Axios实例化对象
	  	this.getCategoryList();

	  	// 获取课程列表
	  	this.courseList();

  }
};
</script>

<style lang="css" scoped>
.course{
	width: 100%;
	height: 1000px;
	background: #f3f3f3;
}
.coursebox{
	padding: 24px 0;
	font-size: 16px;
	color: #666;
	letter-spacing: .41px;
	font-family: PingFangSC-Regular;
	overflow: hidden;
}
.coursebox li{
  padding: 8px;
}
.coursebox li.active{
  border-radius: 5%;
  border: 1px solid orange;
  color: darkorange;
}
ul li{
	float: left;
	margin-right: 24px;
	cursor: pointer;
}

ul li.active{
	color: #00b4e4;
}
.courseList{
	width: 100%;
	height: auto;
	overflow: hidden;
}
.detail{
	float: left;
	width: 248px;
	height: auto;
	margin-right: 16px;
	margin-bottom: 30px;
	position: relative;
	padding: 0 20px;
	background: #fff;
   	 box-shadow: 0 2px 6px 0 #e8e8e8;
   	 transition: all .2s linear;
   	 cursor: pointer;
}
.detail:hover{
	box-shadow: 0 8px 15px rgba(0,0,0,.15);
    	transform: translate3d(0,-3px,0);
}
.detail:nth-of-type(4n){
	margin-right: 0;
}
.head{
	width: 100%;

    	height: 144px;
}
.detail .head img{
	   width: 100%;
	    height: 144px;
	    position: absolute;
	    left: 0;
	    top: 0;
}
.detail .head b{
     width: 100%;
    height: 144px;
    position: absolute;
    left: 0;
    top: 0;
    opacity: .9;
    background: #56CBC4;
}
.detail .head p{
	position: absolute;
	width: 248px;
	height:144px;
	left: 0;
	top: 0;
	text-align: center;
	font-family: PingFangSC-Medium;
	font-size: 24px;
	color: #fff;
	overflow: hidden;
	display: flex;
	align-items:center;
	padding: 0 20px;
	justify-content: space-around;
}
.content{
    width: 248px;

    height: 118px;
    padding-top: 30px;

}
.content p{
     width: 100%;
    height: 40px;
    font-size: 14px;
    color: #666;
    cursor: pointer;
    letter-spacing: .6px;
    margin-bottom: 20px;
    font-family: PingFangSC-Regular;
    overflow: hidden;
}
.content-detail{
	width: 100%;
	height: 40px;
	line-height: 40px;
	position: relative;

}
.content-detail .span3{
	position: absolute;
	right: 0;

}
.content-detail .span3 .s{
	text-decoration: line-through;
}
.content-detail .span4{
	/*margin-left: 100px;*/
	position: absolute;
	right: 0;
	color: #FC0107;
}
.content-detail .span3 .t{
	color: #000;
	margin-left: 5px;
	text-decoration: none !important;
	color: #FC0107;
}

</style>
