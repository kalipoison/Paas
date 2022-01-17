<template>
    <div>
        <div id="content">
            <div id="content_text">
                <b>Notify</b><br>
            </div>
            <div id="content_info">
                <el-table
                    :data="notifyList"
                    stripe>
                    <el-table-column
                        prop="createTime"
                        label="日期"
                        width="200">
                    </el-table-column>
                    <el-table-column
                        prop="notifyContent"
                        label="内容">
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
        this.getNotifylist();
    },
    methods : {
        async getNotifylist () {
            const { data: res } = await this.$http.get('/auth/notify', {
            })
            console.info('getNotifylist', res)
            if (!res.success) {
                return this.$message.error(res.message)
            }
            this.notifyList = res.data;
        },
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
}


</style>