<template>
    <div>
        <div id="content">
            <div id="content_text">
                <b>Notify</b><br>
            </div>
            <div id="content_info">
            <el-table
                :data="notifyData"
                stripe
                style="width: 100%">
                <el-table-column
                prop="date"
                label="日期"
                width="180">
                </el-table-column>
                <el-table-column
                prop="content"
                label="内容"
                width="180">
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
            notifyData : {
                date : '',
                content : '',
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
}
#content_info {
    width: 80%;
    div {
        background: #fff;
    }
}

</style>