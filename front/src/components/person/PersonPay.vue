<template>
    <div>
        <div id="content">
            <div id="content_info">
                <div class="time">
                    <h1>订单编号 : {{orderNum}}</h1>
                    <b>请在剩余</b>
                    <span>{{hour}}</span> : 
                    <span>{{minute}}</span> : 
                    <span>{{seconds}}</span>
                    <b>时间内完成订单支付</b>
                </div>
                <div class="qrcode" ref="qrCodeUrl"></div>
                <el-row>
                    <el-button type="success" @click="handlePay"> 支付完成 </el-button>
                    <el-button type="" @click="handleCancel"> 取消支付 </el-button>
                </el-row>
                
            </div>
        </div>
    </div>
</template>


<script>
import QRCode from 'qrcodejs2'
export default {
    data() {
        return {
            OrderForm : {
                imageName : '',
                day: '',
            },
            hour: '', //倒计时  小时
            minute: '', // 倒计时 分钟
            seconds: '', //倒计时 秒
            interval: '',
            countdownTime: 15,
            orderNum: '',
        }
    },
    mounted() {
        // console.info('store orderNum', this.$store.state.orderNum)
        console.info('store product', this.$store.state.product)
        this.orderNum = this.$store.state.product.orderNum
        this.creatQrCode();
        this.setTimer()
        this.setTimeData(this.countdownTime * 60 * 1000);
    },
    methods : {
        async handlePay () {
            const { data: res } = await this.$http.put('/auth/order/pay', {
                orderNum : this.orderNum,
            });
            if (!res.success) {
                return this.$message.error(res.message)
            }
        },
        async handleCancel () {
            const { data: res } = await this.$http.put('/auth/order/cancel', {
                orderNum : this.orderNum,
            });
            if (!res.success) {
                return this.$message.error(res.message)
            }
        },
        creatQrCode() {
            var qrcode = new QRCode(this.$refs.qrCodeUrl, {
                text: 'xxxx',
                width: 300,
                height: 300,
                colorDark: '#000000',
                colorLight: '#ffffff',
                correctLevel: QRCode.CorrectLevel.H
            })
        },
        setTimeData(cm) {
            console.log(cm, '--cm');
            this.hour = Math.floor((cm / 1000 / 60 / 60) % 24);
            this.hour =
                this.hour.toString().length == 1 ? "0" + this.hour : this.hour;
            this.minute = Math.floor((cm / 1000 / 60) % 60);
            this.minute =
                this.minute.toString().length == 1 ? "0" + this.minute : this.minute;
            this.seconds = Math.floor((cm / 1000) % 60);
            this.seconds =
                this.seconds.toString().length == 1
                    ? "0" + this.seconds
                    : this.seconds;
        },
        setTimer() {
            let _this = this;
            this.interval = setInterval(function () {
                _this.payCountdown(_this.countdownTime * 60 * 1000);
                clearInterval(_this.interval);
            }, 1000);
        },
        /**
         * 支付倒计时
         */
        payCountdown(cm) {
            let _this = this;
            if (cm >= 0 ) {
                this.hour = Math.floor((cm / 1000 / 60 / 60) % 24);
                this.hour =
                    this.hour.toString().length == 1 ? "0" + this.hour : this.hour;
                this.minute = Math.floor((cm / 1000 / 60) % 60);
                this.minute =
                    this.minute.toString().length == 1
                        ? "0" + this.minute
                        : this.minute;
                this.seconds = Math.floor((cm / 1000) % 60);
                this.seconds =
                    this.seconds.toString().length == 1
                        ? "0" + this.seconds
                        : this.seconds;
                let _msThis = this;
                setTimeout(function () {
                    cm -= 1000;
                    _msThis.payCountdown(cm);
                }, 1000);
            }
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
    padding-bottom: 15%;

}

#content_info {
    width: 100%;
    padding-top: 10%;
    padding-bottom: 10%;
    background: #fff;
    text-align: center;
    span {
        color: #000000AD
    }
}
.qrcode {
    width: 300px;
    margin: 0 auto;
    padding-bottom: 5%;
    padding-top: 5%;
}

</style>