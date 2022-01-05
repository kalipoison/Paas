<template>
    <div id="content">
        <div id="login_content">
            <b>Login your account</b><br>
            <span>Or start your free trial</span>
            <div id="login_form">
                <el-form ref="form" :model="form" label-width="80px">
                    <el-form-item label="Email">
                        <el-input v-model="form.email"></el-input>
                    </el-form-item>
                    <el-form-item label="Password">
                        <el-input v-model="form.password"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary">Login</el-button>
                        <el-button>取消</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            form: {
                email: '',
                password: '',
            }
        }
    }
}
</script>

<style lang="less" scoped>
#content {
    width : 100%;
    padding-top: 10%;
    padding-bottom: 10%;
    // padding-left: 33%;
    background: #e2e8f0;
}
#login_content {
    text-align: center;
    padding-left: 33%;
    width: 33%;
    b {
        font-size: 30px;
        color: #1a202c
    }
    span {
        font-size: 14px;
        color: #5a67db;
    }
}
#login_form {
    background: #FFFFFF;
    padding: 10%;
    margin-top: 10%;
}
</style>