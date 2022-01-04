<template>
    <div>
        <el-container>
            <el-header>
                <el-menu
                    class="el-menu-demo"
                    mode="horizontal"
                    background-color="#fff"
                    text-color="#545c64"
                    active-text-color="#ffd04b"
                    router>
                <el-menu-item index="/">Home</el-menu-item>
                <el-menu-item index="/price">Price</el-menu-item>
                <el-menu-item index="/service">Service</el-menu-item>
                </el-menu>
            </el-header>
            <el-main><router-view></router-view></el-main>
            <el-footer><Footer></Footer></el-footer>
        </el-container>
    </div>
</template>

<script>
import Footer from '@/components/Footer'
export default {
    components : {
        Footer
    }
}

</script>

<style lang="less" scoped>
div {
    width: 100%;
    height: 100%;
}



</style>