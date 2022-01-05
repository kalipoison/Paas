<template>
    <div id="content">
        <div id="content_text">
            <el-row><h2>PROTOCOLS</h2></el-row>
            <el-row><b>Flexible Solutions</b></el-row>
            <el-row><span>You can access GLaDOS edge network via V2Ray or Wireguard clients.
                <br>Switch based on your needs anytime.</span></el-row>
            <el-row :style="{padding:'50px'}">
                <el-col :span="10" :offset="1">
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
                                  <span>Mainstream, Best for web and productivity</span>
                              </el-col>
                            </el-row>
                            <el-row :style="{padding:'15px'}">
                              <el-col :span="4"><img src="../../assets/gou.png" width="25px" height="25px"/></el-col>
                              <el-col :span="20">
                                  <span>Split Traffic base on Rules.</span>
                              </el-col>
                            </el-row>
                            <el-row :style="{padding:'15px'}">
                              <el-col :span="4"><img src="../../assets/gou.png" width="25px" height="25px"/></el-col>
                              <el-col :span="20">
                                  <span>Auto selecting best endpoints</span>
                              </el-col>
                            </el-row>
                            <el-row :style="{padding:'15px'}">
                              <el-col :span="4"><img src="../../assets/gou.png" width="25px" height="25px"/></el-col>
                              <el-col :span="20">
                                  <span>For Windows, MacOS, iOS and Android.</span>
                              </el-col>
                            </el-row>
                            <el-row :style="{padding:'15px'}">
                              <el-col :span="4"><img src="../../assets/gou.png" width="25px" height="25px"/></el-col>
                              <el-col :span="20">
                                  <span>Config file subscription</span>
                              </el-col>
                            </el-row>
                            <el-row :style="{padding:'15px'}">
                              <el-col :span="4"><img src="../../assets/gou.png" width="25px" height="25px"/></el-col>
                              <el-col :span="20">
                                  <span>Optimized for speed</span>
                              </el-col>
                            </el-row>
                            <el-button round>Start Today</el-button>
                        </div>
                    </div>
                    </el-card>
                </el-col>
                <el-col :span="10" :offset="2">
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
                                  <span>Backup solution</span>
                              </el-col>
                            </el-row>
                            <el-row :style="{padding:'15px'}">
                              <el-col :span="4"><img src="../../assets/gou.png" width="25px" height="25px"/></el-col>
                              <el-col :span="20">
                                  <span>Best for Gaming and Conference</span>
                              </el-col>
                            </el-row>
                            <el-row :style="{padding:'15px'}">
                              <el-col :span="4"><img src="../../assets/gou.png" width="25px" height="25px"/></el-col>
                              <el-col :span="20">
                                  <span>Full Traffic Routing</span>
                              </el-col>
                            </el-row>
                            <el-row :style="{padding:'15px'}">
                              <el-col :span="4"><img src="../../assets/gou.png" width="25px" height="25px"/></el-col>
                              <el-col :span="20">
                                  <span>Operating at OSI Layer 3</span>
                              </el-col>
                            </el-row>
                            <el-row :style="{padding:'15px'}">
                              <el-col :span="4"><img src="../../assets/gou.png" width="25px" height="25px"/></el-col>
                              <el-col :span="20">
                                  <span>Game, Full ICMP/UDP support</span>
                              </el-col>
                            </el-row>
                            <el-row :style="{padding:'15px'}">
                              <el-col :span="4"><img src="../../assets/gou.png" width="25px" height="25px"/></el-col>
                              <el-col :span="20">
                                  <span>Fixed Datacenter binding</span>
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
    height: 600px;
}

.el-col {
    span {
        font-size: 16px;
        color: #a0aec0;
    }
}

.el-button {
    // margin: 100px;
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
    height: 500px;
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
