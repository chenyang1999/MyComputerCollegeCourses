# 传输层

为什么要有传输层?

为不同主机之间的应用进程提供logical communication

transport protocol run in end-system

sender:把应用层报文封装成 segment,交个网络层

receiver:解封 segment 并重新组装成报文,交给应用层.

Intelnet:TCP and UDP.

只有物理上之间直接相连的通信才叫物理协议,其他都是逻辑协议.

---

网关工作在高层,运行传输层协议,只是为数据的转发提供路径,不负责应用层数据的发送和接受,不属于 Intelnet的 end-system,

网关属于通讯子网,不属于资源子网,整个通信子网为资源子网服务.

网络层实现端到端的连接,服务实体是 IP

传输层也是负责端到端的连接,服务实体的 C/S(应用层的对等实体)

---

different from transport layer to networ layer

network layer:between hosts

transport layer:between processes

both of them are objecting logical communication

---

实际上很多视频的传输是使用 UDP

而HTTP,SMTP 则是使用 TCP 进行传输(对正确性要求高)

---

transport layer is similarity to data link layer.

example:In LAN,younot need networklayer to bulid a connect

---

传输层的重传的基于一个预测机制的.

相邻节点间的重传和端到端都是重传

---

流量控制:

end-to-end 's cache is limited

---

通过软件实现校验和进行差错检测.

而 link layer 用的是硬件(网卡)实现的 CRC 循环冗余校验码.

---

TCP and UDP

TCP is aim to 实现拥塞控制流量控制,提供reliable,order diversity

---

提供复用和分解

connection:1-2,2-3

multiplexing 2->1,2->3(top-down)

,demultiplexing1->2,3->2(bottom-up)

创建 socket 实现复用和分用.

主服务器通过 socket 绑定创建的子进程

![image-20191127143519122](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2019-11-27-063559.png)

UDP socket:(dest IP address,dest port number)二元组

TCP(四元祖目的是支持连接(目的 IP,目的端口,源 IP,源端口),代表 Intel 上的唯一 logical communication,实现 application layer entiries' communication

---

面向连接的复用和分用

TCP socket

TCP 提供一种多线程,分用,全双工的通信方式

---

UPD:纯粹的传输协议,无顺序,无握手,尽力而为的服务,少量的差错检测.

无拥塞控制协议.

UPD 通常为流媒体使用,可以忍受一定的丢包,

DNS 解析

SNMP 

在 UDP 之上通过应用层实现可靠传输.

![image-20191127150338419](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2019-11-27-070959.png)

UPD 是面向报文段的

首部是 8bit

![image-20191127150918571](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2019-11-27-070955.png)

校验需要伪首部,首部和数据

---

什么是可靠的数据传输?

可靠数据传输的重要性,可靠数据传输的原理

无论在应用层,传输层,还是链路层开锁,可靠传输都是很重要的

上层通道在利用下层unreliable channel 的时候如何实现可靠传输

---

可靠数据传输协议:

rdt3.0:停止等待协议

stop-and-wait
$$
t=L/R\\
RTT:recurrun Transport time\\
t=RTT+L/R
$$


---

流水线协议:pipelined protocol

可以同时传递多个 packet

1. gotbackN
2. selective repeat

流水线:增加了带宽的利用率.

![image-20191129134031286](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2019-11-29-054031.png)

![image-20191129134811000](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2019-11-29-054811.png)



---

### TCP:面向字节:可靠的保序的字节流 --没有 “message boundaries” 

点对点 :一个发送方，一个接收方 

流水线 :GBN 和 SR 都有

发送和接收缓冲区 

全双工数据 

面向连接的 

流量控制 

![image-20191129141558369](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2019-11-29-061558.png)

TCP 是全双工,所以既有发送序号,又有确认序号



TCP 的序号和确认号

每个 segment is1000 byte

### 可靠传输的实现

超时重传技术

结论:超时定时器的设置非常重要

RTT:EstimatedRTT=(1-)·EstimatedRTT＋  ·SampleRTT 估值 





























































