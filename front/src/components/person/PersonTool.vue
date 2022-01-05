<template>
    <div>
        <div id="content">
            <div id="content_text">
                <b>Tools</b><br>
            </div>
            <div id="content_info">
                <el-row :style="{padding:'15px'}">
                    <el-col :span="4"><img src="../../assets/gift.png" width="25px" height="25px"/></el-col>
                    <el-col :span="20">
                        <b>Giftcard</b><br>
                        <span>Redeem Giftcard Codes</span>
                    </el-col>
                </el-row>
                <el-row :style="{padding:'15px'}">
                    <el-col :span="4"><img src="../../assets/wall.png" width="25px" height="25px"/></el-col>
                    <el-col :span="20">
                        <b>Security</b><br>
                        <span>implement domain blocker to ensure your cyber-secuirty</span>
                    </el-col>
                </el-row>
                <el-row :style="{padding:'15px'}">
                    <el-col :span="4"><img src="../../assets/wall.png" width="25px" height="25px"/></el-col>
                    <el-col :span="20">
                        <b>Security</b><br>
                        <span>implement domain blocker to ensure your cyber-secuirty</span>
                    </el-col>
                </el-row>
                <el-row :style="{padding:'15px'}">
                    <el-col :span="4"><img src="../../assets/wall.png" width="25px" height="25px"/></el-col>
                    <el-col :span="20">
                        <b>Security</b><br>
                        <span>implement domain blocker to ensure your cyber-secuirty</span>
                    </el-col>
                </el-row>
            </div>
        </div>
    </div>
</template>

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
.el-row {
    margin-top: 30px;
}
</style>