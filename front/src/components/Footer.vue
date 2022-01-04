<template>
    <div id="">
        <el-row :gutter="20">
            <el-col :span="6" :gutter="20" >
                <el-row>
                    <img src="../assets/logo.png" width="50px" height="50px"/>
                </el-row>
                <el-row class="footer-content" :gutter="20" >
                    <span> Reliable Global Paas </span>
                </el-row>
            </el-col>
            <el-col :span="6">
                <el-row class="footer-header" :gutter="20" >
                    <span> PRODUCT </span>
                </el-row>
                <el-row class="footer-content" :gutter="20">
                    <span> Pricing </span>
                </el-row>
                <el-row class="footer-content" :gutter="20" >
                    <span> Type </span>
                </el-row>
            </el-col>
            <el-col :span="6">
                <el-row class="footer-header" :gutter="20" >
                    <span> SUPPORT </span>
                </el-row>
                <el-row class="footer-content" :gutter="20" >
                    <span> Email </span>
                </el-row>
            </el-col>
            <el-col :span="6">
                <el-row class="footer-header" :gutter="20" >
                    <span> COMPANY </span>
                </el-row>
                <el-row class="footer-content" :gutter="20" >
                    <span> About US </span>
                </el-row>
            </el-col>
        </el-row>
    </div>
</template>

<script>
export default{
    name : 'Footer',
    data () {
        return {
            
        }
    }
}
</script>

<style lang="less" scoped>
.el-row {
    text-align: center;
}
.footer-header {
    color: #cbd5e0;
}
.footer-content {
    color: #a0aec0;
}
</style>