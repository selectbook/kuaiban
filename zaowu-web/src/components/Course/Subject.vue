<template>
  <div>
    <el-container style="margin-left: 150px; margin-right:150px">
      <el-main style="margin-top: 50px">
        <el-row :gutter="20" style="margin-bottom:50px">
          <el-col :span="6">
            <el-menu
              default-active="2"
              class="el-menu-vertical-demo"
              @open="handleOpen"
              @close="handleClose"
            >
              <el-menu-item
                v-for="list in headerList"
                :key="list.chapter"
                @click="Fn(list.summary)"
              >
              <template slot="title" style="width:10px">
                {{ list.name | ellipsis}}
              </template>
              </el-menu-item>
            </el-menu>
          </el-col>
          <el-col :span="15" :offset="2">
            <template>
              <div class="demo">
                <video-player
                  class="video-player vjs-custom-skin"
                  ref="videoPlayer"
                  :playsinline="true"
                  :options="playerOptions"
                  @play="onPlayerPlay($event)"
                  @pause="onPlayerPause($event)"
                  @ended="onPlayerEnded($event)"
                  @waiting="onPlayerWaiting($event)"
                  @playing="onPlayerPlaying($event)"
                  @loadeddata="onPlayerLoadeddata($event)"
                  @timeupdate="onPlayerTimeupdate($event)"
                  @canplay="onPlayerCanplay($event)"
                  @canplaythrough="onPlayerCanplaythrough($event)"
                  @statechanged="playerStateChanged($event)"
                  @ready="playerReadied"
                >
                </video-player>
              </div>
            </template>
          </el-col>
        </el-row>
        <!-- <el-divider><i class="el-icon-chat-dot-square"></i>用户讨论</el-divider>
        <el-button
          style="float: right; padding: 3px 0"
          type="text"
          @click="drawer = true"
          >留言讨论</el-button
        >
        <el-row style="margin-top:30px">
          <template>
            <el-table
              :data="tableComment"
              style="width: 100%"
              :default-sort="{ prop: 'date', order: 'descending' }"
            >
              <el-table-column prop="date" label="日期" sortable width="250">
              </el-table-column>
              <el-table-column prop="user" label="姓名" sortable width="200">
              </el-table-column>
              <el-table-column
                prop="content"
                label="评论内容"
                :formatter="formatter"
              >
              </el-table-column>
            </el-table>
          </template>
        </el-row>
        <el-drawer
          :visible.sync="drawer"
          :direction="direction"
          :before-close="handleClose"
        >
          <el-form :inline="true" :model="addComment" class="addComment">
            <el-form-item label="留言评论">
              <el-input
                type="textarea"
                v-model="addComment.content"
                placeholder="请在此处输入您的留言..."
                style="width:800px"
              ></el-input>
            </el-form-item>
            <el-form-item>
              <el-button
                type="primary"
                @click="submitForm('addComment')"
                :loading="loading"
                >{{ loading ? "发表中..." : "发表留言" }}</el-button
              >
            </el-form-item>
          </el-form>
        </el-drawer> -->
      </el-main>
    </el-container>
  </div>
</template>
<script>
// import avater from "vue-avatar-component";
export default {
  name: "AnalyzeReport",
  filters: {
    ellipsis (value) {
      if (!value) return ''
      if (value.length > 11) {
        return value.slice(0,11) + '…'
      }
      return value
    }
  },
  data() {
    return {
      addComment: [],
      title:"wahah",
      drawer: false,
      direction: "btt",
      headerList: [
      ],
      tableComment: [
      ],
      isShow: false,
      playerOptions: {
        playbackRates: [0.5, 1.0, 1.5, 2.0], // 可选的播放速度
        autoplay: false, // 如果为true,浏览器准备好时开始回放。
        muted: false, // 默认情况下将会消除任何音频。
        loop: false, // 是否视频一结束就重新开始。
        preload: "auto", // 建议浏览器在<video>加载元素后是否应该开始下载视频数据。auto浏览器选择最佳行为,立即开始加载视频（如果浏览器支持）
        language: "zh-CN",
        aspectRatio: "16:9", // 将播放器置于流畅模式，并在计算播放器的动态大小时使用该值。值应该代表一个比例 - 用冒号分隔的两个数字（例如"16:9"或"4:3"）
        fluid: true, // 当true时，Video.js player将拥有流体大小。换句话说，它将按比例缩放以适应其容器。
        sources: [
          {
            type: "video/mp4", // 类型
            src:
              "https://cdn.theguardian.tv/webM/2015/07/20/150716YesMen_synd_768k_vp8.webm" // url地址
          }
        ],
        poster: "", // 封面地址
        notSupportedMessage: "对不起，您暂无观看权限...", // "此视频暂无法播放，请稍后再试"允许覆盖Video.js无法播放媒体源时显示的默认信息。
        controlBar: {
          timeDivider: true, // 当前时间和持续时间的分隔符
          durationDisplay: true, // 显示持续时间
          remainingTimeDisplay: false, // 是否显示剩余时间功能
          fullscreenToggle: true // 是否显示全屏按钮
        }
      },
      avatarURL:
        "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif"
    };
  },
  methods: {
    submitForm(formName) {
      this.loading = true;
      this.timer = setTimeout(() => {
        let params = {
          user: localStorage.getItem('userid'),
          course: this.$route.params.detailId,
          content: this.addComment.content
        };
        this.$http
          .postComments(params)
          .then(res => {
            if (res.data.code == 1000) {
              this.$message("留言成功！");
              this.getComment();
              this.drawer = false;
            } else {
              this.$message("留言失败，请重试...");
            }
          })
          .catch(err => {
            this.$message("留言失败，请重试！");
            console.log(err);
          });
        setTimeout(() => {
          this.loading = false;
        }, 400);
      }, 2000);
    },
    handleClose(done) {
      this.$confirm("退出留言？")
        .then(_ => {
          done();
        })
        .catch(_ => {});
    },
    getComment() {
      this.$http
        .getComments(this.$route.params.detailId)
        .then(res => {
          this.tableComment = res.data.data;
        })
        .catch(err => {
          console.log("get comment error: ", err);
        });
    },
    getChapter() {
      this.$http
        .getVedio(this.$route.params.detailId)
        .then(res => {
          this.headerList = res.data.data;
          this.title = res.data.data.name;
          this.playerOptions.sources[0].src = res.data.data[0].summary;
        })
        .catch(err => {
          console.log("get chapter error: ", err);
        });
    },
    //列表click
    Fn(urc) {
      this.playerOptions.sources[0].src = urc;
    },
    // 播放回调
    onPlayerPlay(player) {
      console.log("player play!", player);
    },

    // 暂停回调
    onPlayerPause(player) {
      console.log("player pause!", player);
    },

    // 视频播完回调
    onPlayerEnded($event) {
      console.log(player);
    },

    // DOM元素上的readyState更改导致播放停止
    onPlayerWaiting($event) {
      console.log(player);
    },

    // 已开始播放回调
    onPlayerPlaying($event) {
      console.log(player);
    },

    // 当播放器在当前播放位置下载数据时触发
    onPlayerLoadeddata($event) {
      console.log(player);
    },

    // 当前播放位置发生变化时触发。
    onPlayerTimeupdate($event) {
      console.log(player);
    },

    //媒体的readyState为HAVE_FUTURE_DATA或更高
    onPlayerCanplay(player) {
      // console.log('player Canplay!', player)
    },

    //媒体的readyState为HAVE_ENOUGH_DATA或更高。这意味着可以在不缓冲的情况下播放整个媒体文件。
    onPlayerCanplaythrough(player) {
      // console.log('player Canplaythrough!', player)
    },

    //播放状态改变回调
    playerStateChanged(playerCurrentState) {
      console.log("player current update state", playerCurrentState);
    },

    //将侦听器绑定到组件的就绪状态。与事件监听器的不同之处在于，如果ready事件已经发生，它将立即触发该函数。。
    playerReadied(player) {
      console.log("example player 1 readied", player);
    }
  },
  created() {
    this.getComment();
    this.getChapter();
  }
};
</script>
<style scoped>
.addComment {
  margin-left: 10%;
}
.box > ul {
  display: flex;
  justify-content: center;
}
.order_num p {
  font-size: 16px;
  font-weight: 200;
  color: #7f7f7f;
}

.order_info > ul {
  display: flex;
  justify-content: center;
}

.order_info {
  width: 1200px;
  margin: 0 auto;
}

.order_info > ul {
  width: 100%;
  height: 60px;
  line-height: 60px;
  display: flex;
  justify-content: space-between;
  font-weight: 800;
  font-size: 14px;
  color: #909399;
}

.order_info > ul > li:first-child {
  width: 400px;
}

.item ul li {
  margin-top: 40px;
}
.price {
  color: red;
}
.original {
  text-decoration: line-through;
}
.item {
  text-align: center;
}
#first {
  text-align: left;
}
</style>
