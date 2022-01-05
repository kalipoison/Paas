<template>
    <div>
        <div id="content">
            <div id="content_text">
                <b>My Services</b><br>
                <span>Current plan is Basic Plan, 157 days to be expired.</span>
            </div>
            <div id="content_info">
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
    data () {
        return {
            form : {
                email: '',
                password: '',
            }
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
    padding-bottom: 5%;

}
#content_text {
    // padding: 20px;
    b {
        color: #2185d0;
        font-size: 19.8px;
    }
    span {
        color: #000000de;
        font-size: 15.4px;
    }
}
#content_info {
    background: #f8f8f9;
    width: 40%;
    padding: 10%;
    // margin-top: 10%;
}
</style>