<template>
    <div id="content">
        <div id="content_text">
            <span>PRICING</span><br>
            <b>Simple and Transparent Pricing</b><br>
            <span>All type plans have been designed to fit most people need.</span>
        </div>
        <div id="content_card">
            <b>One account to rule them all</b><br>
            <span>Connect from any platform using one same account.</span>
            <div>
                <div class="card card1">1</div>
                <div class="card card2">1</div>
                <div class="card card3">1</div>
                <div class="nocard"></div>
            </div>
        </div>
    </div>
</template>

<style lang="less" scoped>
#content {
    text-align: center;
    background-color: #3182ce;
    padding: 50px;
}
#content_text {
    b {
        font-size: 36px;
        color: #fff;
    }
    span {
        font-size: 24px;
        color: #e2e8f0;
    }
}
#content_card {
    div {
        padding-left: 15%;
        background-color: #fff;
        .card {
            float: left;
            padding: 50px;
        }
        .nocard {
            clear: both;
        }
        .card1 .card3 {
            width: 20%;
            height: 80px;
        }
        .card2 {
            width: 20%;
            height: 80px;
        }
    }
}
</style>
