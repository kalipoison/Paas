<template>
    <div id="content">
        <div id="content_text">
            <el-row><h2>PRICING</h2></el-row>
            <el-row><b>Simple and Transparent Pricing</b></el-row>
            <el-row><span>All type plans have been designed to fit most people need.</span></el-row>
            <el-row :style="{padding:'50px'}">
                <el-col :span="6" :offset="1">
                    <el-card :body-style="{ padding: '0px' }">
                    <div>
                        <div class="card_header">
                            <el-row :style="{padding:'15px'}">
                                <span>Basic<br></span>
                            </el-row>
                            <el-row :style="{padding:'5px'}">
                                <span>$1.6/month</span>
                            </el-row>
                        </div>
                        <div class="card_content clearfix">
                            <el-row :style="{padding:'15px'}">
                              <el-col :span="4"><img src="../../assets/gou.png" width="25px" height="25px"/></el-col>
                              <el-col :span="20">
                                  <span>3 Devices</span><br>
                                  <span>active simultaneously</span>
                              </el-col>
                            </el-row>
                            <el-row :style="{padding:'15px'}">
                              <el-col :span="4"><img src="../../assets/gou.png" width="25px" height="25px"/></el-col>
                              <el-col :span="20">
                                  <span>5 Datacenters</span><br>
                                  <span>100+ servers pool</span>
                              </el-col>
                            </el-row>
                            <el-row :style="{padding:'15px'}">
                              <el-col :span="4"><img src="../../assets/gou.png" width="25px" height="25px"/></el-col>
                              <el-col :span="20">
                                  <span>3 Devices</span><br>
                                  <span>active simultaneously</span>
                              </el-col>
                            </el-row>
                            <el-button round>Start Today</el-button>
                        </div>
                    </div>
                    </el-card>
                </el-col>
                <el-col :span="6" :offset="2">
                    <el-card :body-style="{ padding: '0px' }">
                    <div>
                        <div class="card_header">
                            <el-row :style="{padding:'15px'}">
                                <span>Pro<br></span>
                            </el-row>
                            <el-row :style="{padding:'5px'}">
                                <span>$4.2/month</span>
                            </el-row>
                        </div>
                        <div class="card_content clearfix">
                            <el-row :style="{padding:'15px'}">
                              <el-col :span="4"><img src="../../assets/gou.png" width="25px" height="25px"/></el-col>
                              <el-col :span="20">
                                  <span>3 Devices</span><br>
                                  <span>active simultaneously</span>
                              </el-col>
                            </el-row>
                            <el-row :style="{padding:'15px'}">
                              <el-col :span="4"><img src="../../assets/gou.png" width="25px" height="25px"/></el-col>
                              <el-col :span="20">
                                  <span>5 Datacenters</span><br>
                                  <span>100+ servers pool</span>
                              </el-col>
                            </el-row>
                            <el-row :style="{padding:'15px'}">
                              <el-col :span="4"><img src="../../assets/gou.png" width="25px" height="25px"/></el-col>
                              <el-col :span="20">
                                  <span>3 Devices</span><br>
                                  <span>active simultaneously</span>
                              </el-col>
                            </el-row>
                            <el-button round>Start Today</el-button>
                        </div>
                    </div>
                    </el-card>
                </el-col>
                <el-col :span="6" :offset="2">
                    <el-card :body-style="{ padding: '0px' }">
                    <div>
                        <div class="card_header">
                            <el-row :style="{padding:'15px'}">
                                <span>Team<br></span>
                            </el-row>
                            <el-row :style="{padding:'5px'}">
                                <span>$18/month</span>
                            </el-row>
                        </div>
                        <div class="card_content clearfix">
                            <el-row :style="{padding:'15px'}">
                              <el-col :span="4"><img src="../../assets/gou.png" width="25px" height="25px"/></el-col>
                              <el-col :span="20">
                                  <span>3 Devices</span><br>
                                  <span>active simultaneously</span>
                              </el-col>
                            </el-row>
                            <el-row :style="{padding:'15px'}">
                              <el-col :span="4"><img src="../../assets/gou.png" width="25px" height="25px"/></el-col>
                              <el-col :span="20">
                                  <span>5 Datacenters</span><br>
                                  <span>100+ servers pool</span>
                              </el-col>
                            </el-row>
                            <el-row :style="{padding:'15px'}">
                              <el-col :span="4"><img src="../../assets/gou.png" width="25px" height="25px"/></el-col>
                              <el-col :span="20">
                                  <span>3 Devices</span><br>
                                  <span>active simultaneously</span>
                              </el-col>
                            </el-row>
                            <el-button round>Start Today</el-button>
                        </div>
                    </div>
                    </el-card>
                </el-col>

            </el-row>
        </div>
    </div>
</template>

<style lang="less" scoped>
#content {
    text-align: center;
    background-color: #3182ce;
    padding-bottom: 10%;
}
h2 {
    color: #e2e8f0;
    font-size: 18px;
}
b {
    font-size: 36px;
    color: #fff;
}
span {
    font-size: 24px;
    color: #e2e8f0;
}

.el-card {
    height: 500px;
}

.el-col {
    span {
        font-size: 16px;
        color: #a0aec0;
    }
}

.el-button {
    margin: 100px;
}

.card_header {
    height: 100px;
    text-align: center;
    background: #fff;
    span {
        color: #1a202c;
    }
}
.card_content {
    height: 400px;
    background: #edf2f7;
    color: #a0a2a0;
    font-size: 16px;
}

.clearfix:before,
.clearfix:after {
    display: table;
    content: "";
}

.clearfix:after {
    clear: both
}
</style>
