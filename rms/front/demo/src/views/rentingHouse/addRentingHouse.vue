<template>
    <el-dialog
        :visible.sync="centerDialogVisible"
        width="30%"
        center>
         <el-col :span="24" class="form-title">
             <p style="color:#0e0f0f;font-size:16px;">新建房子</p>
         </el-col>
         <el-col :span="24" class="form-main">
            <el-form ref="form" :model="RentingHouse" label-width="80px" size="mini">
            <el-form-item label="房屋板块">
                <el-cascader expand-trigger="hover" :options="options" v-model="selectedOptions" @change="handleChange"  :props="props">
                </el-cascader>
            </el-form-item>
            <el-form-item label="具体地址" >
                <el-col :span="12">
                <el-input v-model="RentingHouse.address"></el-input>
                </el-col>   
            </el-form-item>
            <el-form-item label="小区名称" >
                <el-col :span="12">
                <el-input v-model="RentingHouse.community"></el-input>
                </el-col>   
            </el-form-item>
            <el-form-item label="面积(m²)" >
                <el-col :span="12">
                <el-input v-model="RentingHouse.area" placeholder="单位为平方米" ></el-input>
                </el-col>   
            </el-form-item>
            <el-form-item label="户型">
                <el-radio-group v-model="RentingHouse.houseStyle" size="medium">
                <el-radio border label="1">一房</el-radio>
                <el-radio border label="2">二房</el-radio>
                <el-radio border label="3">三房</el-radio>
                <el-radio border label="4">四房</el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="房屋朝向" >
                <el-col :span="12">
                <el-input v-model="RentingHouse.houseFace" placeholder="朝南/坐北向南"></el-input>
                </el-col>   
            </el-form-item>
             <el-form-item label="配套设施" >
                <el-col :span="12">
                <el-input v-model="RentingHouse.supportingFacilities" placeholder="一房一厨一厅一卫"></el-input>
                </el-col>   
            </el-form-item>
            <el-form-item label="出租标题" >
                <el-col :span="12">
                <el-input v-model="RentingHouse.title"></el-input>
                </el-col>   
            </el-form-item>
            <el-form-item label="联系人" >
                <el-col :span="12">
                <el-input v-model="RentingHouse.landlord"></el-input>
                </el-col>   
            </el-form-item>
            <el-form-item label="联系电话" >
                <el-col :span="12">
                <el-input v-model="RentingHouse.phoneNumber"></el-input>
                </el-col>   
            </el-form-item>
            <el-form-item label="出租类型">
                <el-radio-group v-model="RentingHouse.rentingStyle" size="medium">
                <el-radio border label="0">整租</el-radio>
                <el-radio border label="1">合租</el-radio>
                </el-radio-group>
            </el-form-item>
             <el-form-item label="租金" >
                <el-col :span="12">
                <el-input type="number" v-model="RentingHouse.rent"></el-input>
                </el-col>   
            </el-form-item>
            <el-form-item label="租赁情况">
                <el-radio-group v-model="RentingHouse.rantingState" size="medium">
                <el-radio border label="0">已租</el-radio>
                <el-radio border label="1">未租</el-radio>
                <el-radio border label="2">停用</el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="备注">
                <el-col :span="12">
                    <el-input v-model="RentingHouse.remark" type ="textarea"></el-input>
                </el-col>                
            </el-form-item>
            <el-form-item label="上传图片">
                <el-upload class="upload-demo" drag
                    :action="uploadURL"
                    :on-change="handleChange2"
                    :on-success="onUploadSuccess"
                    :file-list="fileList" 
                    :multiple="moreSup"
                >
                <i class="el-icon-upload"></i>
                <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                <div class="el-upload__tip" slot="tip">只能上传单个jpg/png文件，且不超过500kb</div>
                </el-upload>
            </el-form-item>
            <el-form-item size="large">
                <el-button type="primary" class="form-btn-final" @click="onSave">立即创建</el-button>
                <el-button @click.native="onCancel">取消</el-button>
            </el-form-item>
            </el-form>
        </el-col>
</el-dialog>
</template>

<script>
export default{
    data () {
        return {
            RentingHouse: {
                area: '',
                address: '',
                community: '',
                houseStyle: '',
                houseFace: '',
                rentingStyle: '',
                rantingState: '',
                fileDtoList: [],
                provienceId: '',
                cityId: '',
                countryId: '',
                supportingFacilities: '',
                rent: ''
            },
            centerDialogVisible: false, // 是否打开
            moreSup: false,
            options: [], // 城市列表
            fileList: [], // 上传文件列表
            fileName: '',
            uploadURL: this.$backend.getUrl(this.$api.rentingHouse.uploadURL) // 文件上传的url
        }
    },
    mounted () {
        this.onSearch()
	},
    methods: {
        /** 
         * 取消
        */
        onCancel () {
            this.init()
            this.centerDialogVisible = false
            // console.log(this.$parent.add)
            // this.$parent.add = false
        },
        /**
         * 数据校验
         */
        checkData (RentingHouse) {
            return true
        },
        init () {
        this.RentingHouse = {
                area: '',             
                address: '',
                community: '',
                houseStyle: '',
                houseFace: '',
                rentingStyle: '',
                rantingState: '',
                fileDtoList: [],
                provienceId: '',
                cityId: '',
                countryId: '',
                supportingFacilities: '',
                rent: ''
        }
        this.centerDialogVisible = false
        },
        /**
         * 查出城市
         * 
         **/

        onSearch () {
            let self = this
            this.$backend.request(self.$api.cityManager.queryAllCity)
                .then((res) => {
                   self.options = res.data
                   window.sessionStorage.setItem('options', this.options)
                })
         },
        /**
    	 * 上传操作文档
    	 */
    	handleChange2 (f, fileList) {
    		this.fileList = fileList
    	},
    	/**
    	 * 文件上传成功的回调函数
    	 */
    	onUploadSuccess (response, file, fileList) {
    		this.RentingHouse.fileDtoList.push(file.response.data)
    		let f = {files: response.data.files, fileName: response.data.fileName}
    	},
        // 保存
        onSave () {
            let self = this
            this.$backend.request(this.$api.rentingHouse.addRentingHouse, this.RentingHouse).then((result) => {
                if (result.success) {
                        self.$message.success('保存成功！')
                        this.init()
                        self.$parent.$parent.onSearch(1)
						this.centerDialogVisible = false
                }
            })          
        },
        handleChange (value) {
            console.log(this.selectedOptions)
            this.RentingHouse.provienceId = value[0]
            this.RentingHouse.countryId = value[1]
            this.RentingHouse.cityId = value[2]
        }
    }
}
</script>