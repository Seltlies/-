<template>
<div :style='{"width":"80%","padding":"20px","margin":"10px auto","position":"relative","background":"#fff"}'>
    <el-form
	  :style='{"width":"100%","position":"relative"}'
      class="add-update-preview"
      ref="ruleForm"
      :model="ruleForm"
      :rules="rules"
      label-width="100px"
    >
          <el-form-item :style='{"padding":"10px","margin":"0 0 10px","background":"none"}' label="房屋编号" prop="fangwubianhao">
            <el-input v-model="ruleForm.fangwubianhao" 
                placeholder="房屋编号" clearable :disabled=" false  ||ro.fangwubianhao"></el-input>
          </el-form-item>
          <el-form-item :style='{"padding":"10px","margin":"0 0 10px","background":"none"}'  label="户型" prop="huxing">
            <el-select v-model="ruleForm.huxing" placeholder="请选择户型" :disabled=" false  ||ro.huxing" >
              <el-option
                  v-for="(item,index) in huxingOptions"
                  :key="index"
                  :label="item"
                  :value="item">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item :style='{"padding":"10px","margin":"0 0 10px","background":"none"}'  label="房屋类型" prop="fangwuleixing">
            <el-select v-model="ruleForm.fangwuleixing" placeholder="请选择房屋类型" :disabled=" false  ||ro.fangwuleixing" >
              <el-option
                  v-for="(item,index) in fangwuleixingOptions"
                  :key="index"
                  :label="item"
                  :value="item">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item :style='{"padding":"10px","margin":"0 0 10px","background":"none"}' label="图片" v-if="type!='cross' || (type=='cross' && !ro.tupian)" prop="tupian">
            <file-upload
            tip="点击上传图片"
            action="file/upload"
            :limit="3"
            :multiple="true"
            :fileUrls="ruleForm.tupian?ruleForm.tupian:''"
            @change="tupianUploadChange"
            ></file-upload>
          </el-form-item>
            <el-form-item :style='{"padding":"10px","margin":"0 0 10px","background":"none"}' class="upload" v-else label="图片" prop="tupian">
                <img v-if="ruleForm.tupian.substring(0,4)=='http'" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.tupian.split(',')[0]" width="100" height="100">
                <img v-else class="upload-img" style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in ruleForm.tupian.split(',')" :src="baseUrl+item" width="100" height="100">
            </el-form-item>
          <el-form-item :style='{"padding":"10px","margin":"0 0 10px","background":"none"}' label="面积" prop="mianji">
            <el-input v-model="ruleForm.mianji" 
                placeholder="面积" clearable :disabled=" false  ||ro.mianji"></el-input>
          </el-form-item>
          <el-form-item :style='{"padding":"10px","margin":"0 0 10px","background":"none"}' label="售价" prop="shoujia">
			<el-input-number v-model="ruleForm.shoujia" placeholder="售价" :readonly="ro.shoujia"></el-input-number>
          </el-form-item>
          <el-form-item :style='{"padding":"10px","margin":"0 0 10px","background":"none"}'  label="房屋朝向" prop="fangwuchaoxiang">
            <el-select v-model="ruleForm.fangwuchaoxiang" placeholder="请选择房屋朝向" :disabled=" false  ||ro.fangwuchaoxiang" >
              <el-option
                  v-for="(item,index) in fangwuchaoxiangOptions"
                  :key="index"
                  :label="item"
                  :value="item">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item :style='{"padding":"10px","margin":"0 0 10px","background":"none"}'  label="装修情况" prop="zhuangxiuqingkuang">
            <el-select v-model="ruleForm.zhuangxiuqingkuang" placeholder="请选择装修情况" :disabled=" false  ||ro.zhuangxiuqingkuang" >
              <el-option
                  v-for="(item,index) in zhuangxiuqingkuangOptions"
                  :key="index"
                  :label="item"
                  :value="item">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item :style='{"padding":"10px","margin":"0 0 10px","background":"none"}' label="楼层" prop="louceng">
            <el-input v-model="ruleForm.louceng" 
                placeholder="楼层" clearable :disabled=" false  ||ro.louceng"></el-input>
          </el-form-item>
          <el-form-item :style='{"padding":"10px","margin":"0 0 10px","background":"none"}' label="建立时间" prop="jianlishijian">
            <el-input v-model="ruleForm.jianlishijian" 
                placeholder="建立时间" clearable :disabled=" false  ||ro.jianlishijian"></el-input>
          </el-form-item>
          <el-form-item :style='{"padding":"10px","margin":"0 0 10px","background":"none"}'  label="房屋状态" prop="fangwuzhuangtai">
            <el-select v-model="ruleForm.fangwuzhuangtai" placeholder="请选择房屋状态" :disabled=" false  ||ro.fangwuzhuangtai" >
              <el-option
                  v-for="(item,index) in fangwuzhuangtaiOptions"
                  :key="index"
                  :label="item"
                  :value="item">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item :style='{"padding":"10px","margin":"0 0 10px","background":"none"}' label="地理位置" prop="diliweizhi">
            <el-input v-model="ruleForm.diliweizhi" 
                placeholder="地理位置" clearable :disabled=" false  ||ro.diliweizhi"></el-input>
          </el-form-item>
          <el-form-item :style='{"padding":"10px","margin":"0 0 10px","background":"none"}' label="经理账号" prop="jinglizhanghao">
            <el-input v-model="ruleForm.jinglizhanghao" 
                placeholder="经理账号" clearable :disabled=" false  ||ro.jinglizhanghao"></el-input>
          </el-form-item>
          <el-form-item :style='{"padding":"10px","margin":"0 0 10px","background":"none"}' label="经理姓名" prop="jinglixingming">
            <el-input v-model="ruleForm.jinglixingming" 
                placeholder="经理姓名" clearable :disabled=" false  ||ro.jinglixingming"></el-input>
          </el-form-item>
          <el-form-item :style='{"padding":"10px","margin":"0 0 10px","background":"none"}' label="服务详情" prop="fuwuxiangqing">
            <editor 
                :style='{"padding":"0","boxShadow":"none","margin":"0","borderColor":"#ccc","backgroundColor":"#fff","borderRadius":"0","borderWidth":"0","width":"100%","borderStyle":"solid","height":"auto"}'
                v-model="ruleForm.fuwuxiangqing" 
                class="editor" 
                action="file/upload">
            </editor>
          </el-form-item>

      <el-form-item :style='{"padding":"0","margin":"0"}'>
        <el-button :style='{"border":"0","cursor":"pointer","padding":"0","margin":"0 20px 0 0","outline":"none","color":"rgba(255, 255, 255, 1)","borderRadius":"5px","background":"#57A7A5","width":"128px","lineHeight":"40px","fontSize":"16px","height":"40px"}'  type="primary" @click="onSubmit">提交</el-button>
        <el-button :style='{"border":"none","cursor":"pointer","padding":"0","margin":"0","outline":"none","color":"rgba(64, 158, 255, 1)","borderRadius":"5px","background":"#9E9E9E","width":"128px","lineHeight":"40px","fontSize":"16px","height":"40px"}' @click="back()">返回</el-button>
      </el-form-item>
    </el-form>
</div>
</template>

<script>
  export default {
    data() {
	  let self = this
      return {
        id: '',
        baseUrl: '',
        ro:{
				fangwubianhao : false,
				huxing : false,
				fangwuleixing : false,
				tupian : false,
				mianji : false,
				shoujia : false,
				fangwuchaoxiang : false,
				zhuangxiuqingkuang : false,
				louceng : false,
				jianlishijian : false,
				fangwuzhuangtai : false,
				diliweizhi : false,
				fuwuxiangqing : false,
				jinglizhanghao : false,
				jinglixingming : false,
				sfsh : false,
				shhf : false,
				storeupnum : false,
        },
        type: '',
        userTableName: localStorage.getItem('UserTableName'),
        ruleForm: {
          fangwubianhao: '',
          huxing: '',
          fangwuleixing: '',
          tupian: '',
          mianji: '',
          shoujia: '',
          fangwuchaoxiang: '',
          zhuangxiuqingkuang: '',
          louceng: '',
          jianlishijian: '',
          fangwuzhuangtai: '',
          diliweizhi: '',
          fuwuxiangqing: '',
          jinglizhanghao: '',
          jinglixingming: '',
          storeupnum: '',
        },
        huxingOptions: [],
        fangwuleixingOptions: [],
        fangwuchaoxiangOptions: [],
        zhuangxiuqingkuangOptions: [],
        fangwuzhuangtaiOptions: [],


        rules: {
          fangwubianhao: [
          ],
          huxing: [
          ],
          fangwuleixing: [
          ],
          tupian: [
          ],
          mianji: [
          ],
          shoujia: [
            { validator: this.$validate.isNumber, trigger: 'blur' },
          ],
          fangwuchaoxiang: [
          ],
          zhuangxiuqingkuang: [
          ],
          louceng: [
          ],
          jianlishijian: [
          ],
          fangwuzhuangtai: [
          ],
          diliweizhi: [
          ],
          fuwuxiangqing: [
          ],
          jinglizhanghao: [
          ],
          jinglixingming: [
          ],
          sfsh: [
          ],
          shhf: [
          ],
          storeupnum: [
            { validator: this.$validate.isIntNumer, trigger: 'blur' },
          ],
        },
		centerType: false,
      };
    },
    computed: {



    },
    components: {
    },
    created() {
		if(this.$route.query.centerType){
			this.centerType = true
		}
	  //this.bg();
      let type = this.$route.query.type ? this.$route.query.type : '';
      this.init(type);
      this.baseUrl = this.$config.baseUrl;
    },
    methods: {
      getMakeZero(s) {
          return s < 10 ? '0' + s : s;
      },
      // 下载
      download(file){
        window.open(`${file}`)
      },
      // 初始化
      init(type) {
        this.type = type;
        if(type=='cross'){
          var obj = JSON.parse(localStorage.getItem('crossObj'));
          for (var o in obj){
            if(o=='fangwubianhao'){
              this.ruleForm.fangwubianhao = obj[o];
              this.ro.fangwubianhao = true;
              continue;
            }
            if(o=='huxing'){
              this.ruleForm.huxing = obj[o];
              this.ro.huxing = true;
              continue;
            }
            if(o=='fangwuleixing'){
              this.ruleForm.fangwuleixing = obj[o];
              this.ro.fangwuleixing = true;
              continue;
            }
            if(o=='tupian'){
              this.ruleForm.tupian = obj[o].split(",")[0];
              this.ro.tupian = true;
              continue;
            }
            if(o=='mianji'){
              this.ruleForm.mianji = obj[o];
              this.ro.mianji = true;
              continue;
            }
            if(o=='shoujia'){
              this.ruleForm.shoujia = obj[o];
              this.ro.shoujia = true;
              continue;
            }
            if(o=='fangwuchaoxiang'){
              this.ruleForm.fangwuchaoxiang = obj[o];
              this.ro.fangwuchaoxiang = true;
              continue;
            }
            if(o=='zhuangxiuqingkuang'){
              this.ruleForm.zhuangxiuqingkuang = obj[o];
              this.ro.zhuangxiuqingkuang = true;
              continue;
            }
            if(o=='louceng'){
              this.ruleForm.louceng = obj[o];
              this.ro.louceng = true;
              continue;
            }
            if(o=='jianlishijian'){
              this.ruleForm.jianlishijian = obj[o];
              this.ro.jianlishijian = true;
              continue;
            }
            if(o=='fangwuzhuangtai'){
              this.ruleForm.fangwuzhuangtai = obj[o];
              this.ro.fangwuzhuangtai = true;
              continue;
            }
            if(o=='diliweizhi'){
              this.ruleForm.diliweizhi = obj[o];
              this.ro.diliweizhi = true;
              continue;
            }
            if(o=='fuwuxiangqing'){
              this.ruleForm.fuwuxiangqing = obj[o];
              this.ro.fuwuxiangqing = true;
              continue;
            }
            if(o=='jinglizhanghao'){
              this.ruleForm.jinglizhanghao = obj[o];
              this.ro.jinglizhanghao = true;
              continue;
            }
            if(o=='jinglixingming'){
              this.ruleForm.jinglixingming = obj[o];
              this.ro.jinglixingming = true;
              continue;
            }
            if(o=='storeupnum'){
              this.ruleForm.storeupnum = obj[o];
              this.ro.storeupnum = true;
              continue;
            }
          }
        }else if(type=='edit'){
			this.info()
		}
        // 获取用户信息
        this.$http.get(this.userTableName + '/session', {emulateJSON: true}).then(res => {
          if (res.data.code == 0) {
            var json = res.data.data;
            if((json.jinglizhanghao!=''&&json.jinglizhanghao) || json.jinglizhanghao==0){
                this.ruleForm.jinglizhanghao = json.jinglizhanghao
            }
            if((json.jinglixingming!=''&&json.jinglixingming) || json.jinglixingming==0){
                this.ruleForm.jinglixingming = json.jinglixingming
            }
          }
        });
        this.huxingOptions = "小户型,大户型".split(',')
        this.$http.get('option/fangwuleixing/fangwuleixing', {emulateJSON: true}).then(res => {
          if (res.data.code == 0) {
            this.fangwuleixingOptions = res.data.data;
          }
        });
        this.fangwuchaoxiangOptions = "南北,东西".split(',')
        this.zhuangxiuqingkuangOptions = "简装,精装,未装修".split(',')
        this.fangwuzhuangtaiOptions = "在售,已售".split(',')

		if (localStorage.getItem('raffleType') && localStorage.getItem('raffleType') != null) {
			localStorage.removeItem('raffleType')
			setTimeout(() => {
				this.onSubmit()
			}, 300)
		}
      },

    // 多级联动参数
      // 多级联动参数
      info() {
        this.$http.get(`fangchanxinxi/detail/${this.$route.query.id}`, {emulateJSON: true}).then(res => {
          if (res.data.code == 0) {
            this.ruleForm = res.data.data;
          }
        });
      },
      // 提交
      onSubmit() {

			//更新跨表属性
			var crossuserid;
			var crossrefid;
			var crossoptnum;
			this.$refs["ruleForm"].validate(valid => {
				if(valid) {
					if(this.type=='cross'){
						var statusColumnName = localStorage.getItem('statusColumnName');
						var statusColumnValue = localStorage.getItem('statusColumnValue');
						if(statusColumnName && statusColumnName!='') {
							var obj = JSON.parse(localStorage.getItem('crossObj'));
							if(!statusColumnName.startsWith("[")) {
								for (var o in obj){
									if(o==statusColumnName){
										obj[o] = statusColumnValue;
									}
								}
								var table = localStorage.getItem('crossTable');
								this.$http.post(table+'/update', obj).then(res => {});
							} else {
								crossuserid=Number(localStorage.getItem('frontUserid'));
								crossrefid=obj['id'];
								crossoptnum=localStorage.getItem('statusColumnName');
								crossoptnum=crossoptnum.replace(/\[/,"").replace(/\]/,"");
							}
						}
					}
					if(crossrefid && crossuserid) {
						this.ruleForm.crossuserid=crossuserid;
						this.ruleForm.crossrefid=crossrefid;
						var params = {
							page: 1,
							limit: 10,
							crossuserid:crossuserid,
							crossrefid:crossrefid,
						}
						this.$http.get('fangchanxinxi/list', {
							params: params
						}).then(res => {
							if(res.data.data.total>=crossoptnum) {
								this.$message({
									message: localStorage.getItem('tips'),
									type: 'error',
									duration: 1500,
								});
								return false;
							} else {
								// 跨表计算


								this.$http.post(`fangchanxinxi/${this.ruleForm.id?'update':this.centerType?'save':'add'}`, this.ruleForm).then(res => {
									if (res.data.code == 0) {
										this.$message({
											message: '操作成功',
											type: 'success',
											duration: 1500,
											onClose: () => {
												this.$router.go(-1);
											}
										});
									} else {
										this.$message({
											message: res.data.msg,
											type: 'error',
											duration: 1500
										});
									}
								});
							}
						});
					} else {


						this.$http.post(`fangchanxinxi/${this.ruleForm.id?'update':this.centerType?'save':'add'}`, this.ruleForm).then(res => {
							if (res.data.code == 0) {
								this.$message({
									message: '操作成功',
									type: 'success',
									duration: 1500,
									onClose: () => {
										this.$router.go(-1);
									}
								});
							} else {
								this.$message({
									message: res.data.msg,
									type: 'error',
									duration: 1500
								});
							}
						});
					}
				}
			});
		},
		// 获取uuid
		getUUID () {
			return new Date().getTime();
		},
		// 返回
		back() {
			this.$router.go(-1);
		},
      tupianUploadChange(fileUrls) {
          this.ruleForm.tupian = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");;
      },
    }
  };
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.el-date-editor.el-input {
		width: auto;
	}
	
	.add-update-preview .el-form-item /deep/ .el-form-item__label {
	  padding: 0 10px 0 0;
	  color: #000;
	  font-weight: 500;
	  width: 100px;
	  font-size: 14px;
	  line-height: 40px;
	  text-align: right;
	}
	
	.add-update-preview .el-form-item /deep/ .el-form-item__content {
	  margin-left: 100px;
	}
	
	.add-update-preview .el-input /deep/ .el-input__inner {
	  border: 1px solid #E2E3E5;
	  border-radius: 0;
	  padding: 0 12px;
	  box-shadow: none;
	  outline: none;
	  color: #000;
	  width: 500px;
	  font-size: 14px;
	  height: 40px;
	}
	.add-update-preview .el-input-number /deep/ .el-input__inner {
		text-align: left;
	  border: 1px solid #E2E3E5;
	  border-radius: 0;
	  padding: 0 12px;
	  box-shadow: none;
	  outline: none;
	  color: #000;
	  width: 500px;
	  font-size: 14px;
	  height: 40px;
	}
	.add-update-preview .el-input-number /deep/ .el-input-number__decrease {
		display: none;
	}
	.add-update-preview .el-input-number /deep/ .el-input-number__increase {
		display: none;
	}
	
	.add-update-preview .el-select /deep/ .el-input__inner {
	  border: 1px solid #E2E3E5;
	  border-radius: 0;
	  padding: 0 10px;
	  box-shadow: none;
	  outline: none;
	  color: #000;
	  width: 500px;
	  font-size: 14px;
	  height: 40px;
	}
	
	.add-update-preview .el-date-editor /deep/ .el-input__inner {
	  border: 1px solid #E2E3E5;
	  border-radius: 0;
	  padding: 0 10px 0 30px;
	  box-shadow: none;
	  outline: none;
	  color: none;
	  width: 500px;
	  font-size: 14px;
	  height: 40px;
	}
	
	.add-update-preview /deep/ .el-upload--picture-card {
		background: transparent;
		border: 0;
		border-radius: 0;
		width: auto;
		height: auto;
		line-height: initial;
		vertical-align: middle;
	}
	
	.add-update-preview /deep/ .upload .upload-img {
	  border: 1px solid #E2E3E5;
	  cursor: pointer;
	  border-radius: 0;
	  color: #000;
	  width: 200px;
	  font-size: 32px;
	  line-height: 60px;
	  text-align: center;
	  height: 60px;
	}
	
	.add-update-preview /deep/ .el-upload-list .el-upload-list__item {
	  border: 1px solid #E2E3E5;
	  cursor: pointer;
	  border-radius: 0;
	  color: #000;
	  width: 200px;
	  font-size: 32px;
	  line-height: 60px;
	  text-align: center;
	  height: 60px;
	}
	
	.add-update-preview /deep/ .el-upload .el-icon-plus {
	  border: 1px solid #E2E3E5;
	  cursor: pointer;
	  border-radius: 0;
	  color: #000;
	  width: 200px;
	  font-size: 32px;
	  line-height: 60px;
	  text-align: center;
	  height: 60px;
	}
	
	.add-update-preview .el-textarea /deep/ .el-textarea__inner {
	  border: 1px solid #E2E3E5;
	  border-radius: 0;
	  padding: 12px;
	  box-shadow: none;
	  outline: none;
	  color: #000;
	  width: 500px;
	  font-size: 14px;
	  height: 120px;
	}
</style>
