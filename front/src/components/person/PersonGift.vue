<template>
    <div>
        <div id="content">
            <div id="content_text">
                <h1>Gifted</h1>
                <span style="color:#00000099">Redeem Basic Plan</span><br>
                <span style="color:#00000099">通过礼品码兑换礼品</span>
                <div id="voucher_content">
                    <el-form
                        :model="voucherForm"
                        ref="voucherFormRef"
                        label-width="70px"
                    >
                        <el-form-item label="兑换码" prop="voucherCode">
                            <el-input v-model="voucherForm.voucherCode" placeholder="XXX-XXX-XXX"></el-input>
                        </el-form-item>
                        <el-button type="primary" @click="useVoucher">确 定</el-button>
                    </el-form>
                </div>
            </div>
        </div>
    </div>
</template>


<script>
export default {
    data() {
        return {
            voucherForm : {
                voucherCode : '',
            }
        }
    },
    methods : {
        async useVoucher() {
            console.info('voucherForm.voucherCode', )
            const voucherCode = this.voucherForm.voucherCode
            if (voucherCode.length !== 11 || voucherCode.indexOf('-') === -1) {
                this.$message.error('格式不对');
            } else {
                const username = window.sessionStorage.getItem('username');
                console.info('username', username)
                const { data: res } = await this.$http.post('/client/voucher', 
                {
                    voucherCode : voucherCode,
                    email : username
                })
                console.info('res', res)
                if (res.statusCode !== 200) {
                    this.$message.error(res.message)
                } else {
                    this.$message.success(res.data)
                }
            }
        }
    }
}
</script>

<style lang="less" scoped>
#content {
    background: #ebf8ff;
    height: 80%;
}

#content_text {
    // padding: 20px;
    height: 700px;
    padding-left: 5%;
    padding-right: 5%;
    padding-top: 5%;
}

#voucher_content {
    text-align: right;
    margin-top: 2%;
    padding-top: 5%;
    padding-bottom: 3%;
    background: #f3f4f5;
    width: 100%;
}

.el-form {
    padding-left: 5%;
    padding-right: 5%;
}

</style>