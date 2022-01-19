<template>
    <div>
        <div id="content">
            <div id="content_text">
                <b>My Services</b><br>
                <span>Current plan is Basic Plan, 157 days to be expired.</span>
            </div>
            <div id="content_info">
                <el-table
                    :data="notifyList"
                    stripe>
                    <el-table-column
                        prop="containerName"
                        label="Container Name">
                    </el-table-column>
                    <el-table-column
                        prop="image"
                        label="Image">
                    </el-table-column>
                    <el-table-column
                        prop="productName"
                        label="Product Name">
                    </el-table-column>
                    <el-table-column
                        prop="expireTime"
                        label="Expire Time">
                    </el-table-column>
                    <el-table-column
                        label="Terminate">
                        <template slot-scope="scope">
                        <a href="#" @click="handleTerm(scope.row)"><b>Enter Terminate</b></a>
                        </template>
                        <!-- <el-tooltip
                            class="item"
                            effect="dark"
                            content="进入终端"
                            :enterable="false"
                            placement="top"
                        >
                            <el-button
                            type="success"
                            icon="el-icon-more"
                            size="mini"
                            content="进入终端"
                            circle
                            @click="InfoYamlDeployment(scope.row)"
                            ></el-button>
                        </el-tooltip> -->
                    </el-table-column>
                </el-table>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data () {
        return {
            notifyList : [],
        }
    },
    created() {
        this.getUserProdlist();
    },
    methods : {
        async getUserProdlist () {
            const username = window.sessionStorage.getItem('username')
            console.info('username' , username)
            const { data: res } = await this.$http.get('/auth/userProd', {
                username : username
            })
            console.info('getNotifylist', res)
            if (!res.success) {
                return this.$message.error(res.message)
            }
            this.notifyList = res.data;
        },
        handleTerm (serviceInfo) {
            console.info('触发进入终端', serviceInfo)
            this.$router.push('/terminal')
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
    width: 100%;
    // padding: 10%;
    // margin-top: 10%;
}
</style>