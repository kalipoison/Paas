<template>
    <div>
        <el-container>
            <el-header>
                <div id="header_nav">
                    <el-menu
                        class=""
                        mode="horizontal"
                        background-color="#fff"
                        text-color="#545c64"
                        active-text-color="#ffd04b"
                        router>
                        <el-menu-item index="/home">Home</el-menu-item>
                        <el-menu-item index="/shop">Shop</el-menu-item>
                        <el-menu-item index="/tool">Tool</el-menu-item>
                        <el-menu-item index="/notify">Notify</el-menu-item>
                    </el-menu>
                </div>
                <div id="header_person">
                    <el-menu
                        class=""
                        mode="horizontal"
                        background-color="#fff"
                        text-color="#545c64"
                        active-text-color="#ffd04b"
                        router>
                        <el-submenu index="2-4">
                            <template slot="title">1174616074@qq.com</template>
                            <el-menu-item index="2-4-1">logout</el-menu-item>
                        </el-submenu>
                    </el-menu>
                </div>
            </el-header>
            <el-main><router-view></router-view></el-main>
            <el-footer><Footer></Footer></el-footer>
        </el-container>
    </div>
</template>

<script>
import Footer from '@/components/Footer'
export default {
    data() {
        return {
            
        } 
    },
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
.el-container {
    padding: 0px;
    margin: 0px;
}
.el-header {
    padding: 0px;
    margin: 0px;
}
.el-main {
    padding: 0px;
    margin: 0px;
}
#header_nav {
    width: 80%;
    float: left;
}
#header_person {
    width: 20%;
    float: left;
    height: 100%;
    vertical-align: middle;
}
</style>