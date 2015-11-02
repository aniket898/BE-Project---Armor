LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE := net_linksocket
LOCAL_LDLIBS :=  -llog 
LOCAL_SRC_FILES := net_linksocket.c

include $(BUILD_SHARED_LIBRARY)