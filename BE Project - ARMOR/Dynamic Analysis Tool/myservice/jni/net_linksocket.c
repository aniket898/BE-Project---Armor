//#include "squared.h"
#include <jni.h>
#include <sys/socket.h>
#include <linux/netlink.h>
#include <android/log.h>
#include <sys/types.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>

//#define  LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG,DEBUG_TAG,__VA_ARGS__)

#define NETLINK_USER 31
# define NETLINK_NITRO 17
struct sockaddr_nl src_addr, dest_addr;
struct nlmsghdr *nlh = NULL;
struct iovec iov;
int sock_fd;
struct msghdr msg;
#define MAX_PAYLOAD 1024 /* maximum payload size*/

JNIEXPORT jstring JNICALL Java_com_example_myservice_Myservice_netlinkconnect(JNIEnv * je, jclass jc,jint uid)
{

	struct sockaddr_nl s_nladdr, d_nladdr;
		struct msghdr msg ;
		struct nlmsghdr *nlh=NULL ;
		struct iovec iov;
		int fd=socket(AF_NETLINK ,SOCK_RAW , NETLINK_NITRO );
		char u_id[10],message[30];
		sprintf(u_id,"%d",uid);
		strcat(message,u_id);
		__android_log_print(ANDROID_LOG_VERBOSE, "MyApp", "Message : %s",u_id);
		/*if(sock_fd<0)
				return (-1);*/
		//__android_log_print(ANDROID_LOG_VERBOSE, "MyApp", "socket Created");
		/* source address */
		memset(&s_nladdr, 0 ,sizeof(s_nladdr));
		s_nladdr.nl_family= AF_NETLINK ;
		s_nladdr.nl_pad=0;
		s_nladdr.nl_pid = getpid();

		int status=bind(fd, (struct sockaddr*)&s_nladdr, sizeof(s_nladdr));
		if(status<0)
		{
			__android_log_print(ANDROID_LOG_VERBOSE, "MyApp", "Failed Bind");
				//return(status);
		}
		//__android_log_print(ANDROID_LOG_VERBOSE, "MyApp", "socket Bound");
			//return(status);
		memset(&d_nladdr, 0 ,sizeof(d_nladdr));
		d_nladdr.nl_family= AF_NETLINK ;
		d_nladdr.nl_pad=0;
		d_nladdr.nl_pid = 0; /* destined to kernel */

		/* Fill the netlink message header */
		nlh = (struct nlmsghdr *)malloc(100);
		memset(nlh , 0 , 100);

		//__android_log_print(ANDROID_LOG_VERBOSE, "MyApp", "Msg is %s",message);
		strcpy(NLMSG_DATA(nlh), u_id);
		nlh->nlmsg_len =100;
		nlh->nlmsg_pid = getpid();
		nlh->nlmsg_flags = 1;
		nlh->nlmsg_type = 0;

		/*iov structure */

		iov.iov_base = (void *)nlh;
		iov.iov_len = nlh->nlmsg_len;

		/* msg */
		memset(&msg,0,sizeof(msg));
		msg.msg_name = (void *) &d_nladdr ;
		msg.msg_namelen=sizeof(d_nladdr);
		msg.msg_iov = &iov;
		msg.msg_iovlen = 1;
		__android_log_print(ANDROID_LOG_VERBOSE, "MyApp", "Sending Message %s",NLMSG_DATA(nlh));//,NLMSG_DATA(nlh));
		sendmsg(fd, &msg, 0);
		__android_log_print(ANDROID_LOG_VERBOSE, "MyApp","Waiting for message from kernel\n");
		/* Read message from kernel */
		    recvmsg(fd, &msg, 0);
		   // __android_log_print(ANDROID_LOG_VERBOSE, "MyApp"," Received message payload: %s\n",NLMSG_DATA(nlh));
		    close(fd);
		    return (*je)->NewStringUTF(je,NLMSG_DATA(nlh));
		//return (EXIT_SUCCESS);
}
