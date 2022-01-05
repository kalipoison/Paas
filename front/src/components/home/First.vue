<template>
    <div id="content">
        <div id="content_one">
            <div>
                <b>Master your network with <span>ME</span></b><br>
                <div>
                    We provides most fast, stable, and secure endpoints for users to access
                    <br> 
                    high speed inter-national Internet, Netflix and Privacy with confidence.
                </div>
                <div>
                    <el-button type="primary" round @click="handleLoginOrRegister">Register</el-button>
                    <el-button round @click="handleLoginOrRegister">Login</el-button>
                </div>
            </div>
        </div>
        <div id="content_two">
            <div>
                <b>Generic Local and Destination Operating System</b><br>
                <span>Smart cloud service system trusted by users and teams all over the world</span>
                <div>
                    <div class="card"><b>99.99%</b><br><span>SLA</span></div>
                    <div class="card"><b>10</b><br><span>Days Uptimes</span></div>
                    <div class="card"><b>200+</b><br><span>Servers</span></div>
                    <div class="nocard"></div>
                </div>
            </div>
        </div>
        <div id="content_three">
            <div>
                <b>One account to rule them all</b><br>
                <span>Connect from any platform using one same account.</span>
                <div>
                    <div class="card"><img src="../../assets/centos.png"/></div>
                    <div class="card"><img src="../../assets/redhat.png"/></div>
                    <div class="card"><img src="../../assets/ubuntu.png"/></div>
                    <div class="nocard"></div>
                </div>
            </div>
        </div>
    </div>

</template>

<script>
export default {
    data () {
        return {
            
        }
    },
    methods: {
        handleLoginOrRegister () {
            this.$router.push('/login')
        }
    }
}
</script>

<style lang="less" scoped>
#content {
    width: 100%;
}

#content_one {
    text-align: center;
    background-color: #ebf8ff;
    div {
        b {
            font-size: 64px;
            span {
                color: #4299e1;
            }
        }
        div {
            color: #718096;
            font-size: 20px;
        }
        .el-button {
            width: 150px;
            height: 50px;
            font-size: 20px;
        }
        padding: 50px;
    }
}

#content_two {
    background-color: #4299e1;
    text-align: center;
    padding: 50px;
    div {
        b {
            color: #FFFFFF;
            font-size: 36px;
        }
        span {
            color : #bee3f8;
            font-size: 20px;
        }
        div {
            padding-left: 20%;
            .card {
                float: left;
                padding: 50px;
            }
            .nocard {
                clear: both;
            }
        }
    }
}

#content_three {
    background-color: #fff;
    text-align: center;
    padding: 50px;
    div {
        b {
            color: #2d3748;
            font-size: 64px;
        }
        span {
            color : #a0aec0;
            font-size: 20px;
        }
        div {
            padding-left: 15%;
            .card {
                float: left;
                padding: 50px;
            }
            .nocard {
                clear: both;
            }
        }
    }
}
</style>

