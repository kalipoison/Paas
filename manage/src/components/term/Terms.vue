<template>
  <div>
    <!-- 面包屑导航区 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>终端</el-breadcrumb-item>
      <el-breadcrumb-item>终端</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 卡片视图 -->
    <el-card>
      <div id="terminal" ref="terminalRef" style="width:100%; height:100%"></div>
    </el-card>
  </div>

</template>
<script>
import 'xterm/css/xterm.css'
import { Terminal } from "xterm"
import { FitAddon } from 'xterm-addon-fit'
import "xterm/css/xterm.css"

export default {
  data () {
    return {
      term: "", // 保存terminal实例
      rows: 30,
      cols: 140,
      operate: '',
      host : '192.168.91.157',
      port: '22',
      username: 'root',
      password: '12345',
      webSocket: '',
      socketURI: 'ws://localhost:8082/api/auth/websocket',
      // socketURI: 'wss://localhost:8082/socket', // https
    }
  },
  mounted () {
    this.initSocket()
  },
  beforeDestroy () {
    // this.websocketclose()
  },
  methods: {
    initTerm() {
      var term = new Terminal({
          cols: 97,
          rows: 37,
          cursorBlink: true, // 光标闪烁
          cursorStyle: "block", // 光标样式  null | 'block' | 'underline' | 'bar'
          scrollback: 800, //回滚
          tabStopWidth: 8, //制表宽度
          screenKeys: true
      });
      const fitAddon = new FitAddon();
      term.loadAddon(fitAddon)
      // 创建terminal实例
      term.open(this.$refs.terminalRef)
      term.writeln('Welcome to use WebSSH! Paas platform')
      term.writeln('Connecting ....')
      fitAddon.fit()
      this.term = term
      term.onData(key => {  // 输入与粘贴的情况
        this.sendShell(key)
      })
    },
    sendShell (data) {
      let _bar = {
        operate:'command',
        command: data,
        userId: 1024
      }
      console.info('sendShell', data)
      this.webSocket.send(JSON.stringify({"operate": "command", "command": data}))
    },
    writeShell(data) {
      this.term.write(data)
    },
    initSocket() {
      this.initTerm();
      this.webSocket = new WebSocket(this.socketURI);
      this.webSocket.onopen = this.websocketonopen;
      this.webSocket.onerror = this.websocketonerror;
      this.webSocket.onmessage = this.websocketonmessage; 
      this.webSocket.onclose = this.websocketclose;
      console.info('this.webSocket', this.webSocket)
    },
    websocketonopen() {
      console.log("WebSocket连接成功");
      this.webSocket.send(JSON.stringify({
        operate:'connect',
        host: this.host,
        port: this.port,
        username: this.username,
        password: this.password,
      }));
    },
    websocketonerror(e) { //错误
      console.log("WebSocket连接发生错误");
    },
    websocketonmessage(e){ //数据接收 
      console.log('onmessage',e)
      this.term.write(e.data)
    }, 
    websocketsend(agentData){//数据发送 
      console.log('onsend',agentData); 
    }, 
    websocketclose(e){ //关闭 
      console.log("connection closed ()"); 
    },

  }
}
</script>