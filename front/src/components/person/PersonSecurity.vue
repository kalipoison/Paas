<template>
    <div>
        <div id="content">
            <div id="content_text">
                <b>Cyber Security</b><br>
            </div>
            <div id="content_info">
                <h1>We implements domain blocker in our network to ensure your cyber-security</h1>
                <p>We stop websites and services for your safety. We stop theses content:</p>
                <ol>
                    <li>Fakenews.</li>
                    <li>Untrusted Indentity Trackers.</li>
                    <li>Malwares.</li>
                    <li>State owned Political Media and Propaganda Agency.</li>
                    <li>Children pornography.</li>
                    <li>Cult, Terrorism, or Hate-related materials.</li>
                </ol>
                <p>The restricted services categories may be imposed through Network Rules, Self-regulation or the requirements of our Internet Services Providers.</p>
                <p>Domain Blocker is the most pragmatic and reasonable approach to ensure our policies: without censoring your content, We stop your connection. And we bring together cyber threat intelligence about malicious domains from a variety of public and private sources and block access to those malicious domains when your system attempts to contact them. When you use GLaDOS, attackers and malware cannot leverage the known malicious domains to control your systems, and their ability to steal your data or cause harm will be hindered.</p>
            </div>
        </div>
    </div>
</template>


<script>
export default {
    data() {
        return {

        }
    },
    methods : {
        handleGift () {
            console.info('handleGift');
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
    padding-bottom: 15%;

}

#content_text {
    // padding: 20px;
    b {
        color: #2185d0;
        font-size: 19.8px;
    }
}

#content_info {
    li {
        padding: 5px;
    }
}
</style>