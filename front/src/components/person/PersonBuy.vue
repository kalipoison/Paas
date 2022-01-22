<template>
    <div>
        <div id="content">
            <div id="content_text">
                <b>Purchase</b><br>
            </div>
            <div id="content_info">
                <el-row :style="{padding:'20px'}">
                    <el-form
                        :model="OrderForm"
                        ref="OrderFormRef"
                        label-width="100px"
                    >
                        <h3>Step 1 : Choose A Image For Your Container</h3>
                        <el-form-item label="" prop="image">
                            <el-radio v-model="OrderForm.imageName" label="centos7">Centos7</el-radio>
                            <el-radio v-model="OrderForm.imageName" label="centos6">Centos6</el-radio>
                            <el-radio v-model="OrderForm.imageName" label="Ubuntu18">Ubuntu 18.0</el-radio>
                            <el-radio v-model="OrderForm.imageName" label="Ubuntu21">Ubuntu 21.0</el-radio>
                            <el-radio v-model="OrderForm.imageName" label="Debian">Debian</el-radio>
                        </el-form-item>
                        <h3>Step 2 : Choose a billing method</h3>
                            <el-form-item label="" prop="day">
                                <el-radio v-model="OrderForm.day" label="30">$30 FOR 30 DAY </el-radio>
                                <el-radio v-model="OrderForm.day" label="60">$70 FOR 180 DAY</el-radio>
                                <el-radio v-model="OrderForm.day" label="90">$100 FOR 360 DAY</el-radio>
                            </el-form-item>
                        <h3>Step 3 : Purchase your plan</h3>
                        <el-form-item>
                            <el-button type="success" @click="handleAliPay()">AliPay</el-button>
                            <el-button type="primary" @click="handleWxPay()">Wechat Pay</el-button>
                            <el-button type="info" @click="handleOther()">Other</el-button>
                        </el-form-item>
                    </el-form>
                </el-row>
            </div>
        </div>
    </div>
</template>


<script>
export default {
    data() {
        return {
            OrderForm : {
                imageName : '',
                day: '',
            }
        }
    },
    methods : {
        handleAliPay() {
            const product = this.$store.state.product.product
            product.payType = 2
            
            console.info('aliPay', product)
        },
        handleWxPay() {
            const product = this.$store.state.product.product
            product.payType = 1
        },
        handleOther() {
            const product = this.$store.state.product.product
            product.payType = 0
        }
    }
}
</script>

<style lang="less" scoped>
#content {
    background: #ebf8ff;
    padding-left: 10%;
    padding-right: 10%;
    padding-top: 5%;
    padding-bottom: 15%;

}

#content_text {
    // padding: 20px;
    b {
        color: #2185d0;
        font-size: 19.8px;
    }
}
#content_info {
    width: 80%;
    background: #fff;
    span {
        color: #000000AD
    }
}
.el-row {
    margin-top: 30px;
}
</style>