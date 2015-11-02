#include <linux/module.h>
#include <linux/vermagic.h>
#include <linux/compiler.h>

MODULE_INFO(vermagic, VERMAGIC_STRING);

struct module __this_module
__attribute__((section(".gnu.linkonce.this_module"))) = {
 .name = KBUILD_MODNAME,
 .init = init_module,
#ifdef CONFIG_MODULE_UNLOAD
 .exit = cleanup_module,
#endif
 .arch = MODULE_ARCH_INIT,
};

static const struct modversion_info ____versions[]
__used
__attribute__((section("__versions"))) = {
	{ 0x60547ef, "struct_module" },
	{ 0x6b86712e, "netlink_unicast" },
	{ 0x328a05f1, "strncpy" },
	{ 0xfa2a45e, "__memzero" },
	{ 0x14e11cad, "skb_put" },
	{ 0x893c6a24, "__alloc_skb" },
	{ 0x97255bdf, "strlen" },
	{ 0xf9a482f9, "msleep" },
	{ 0x1000e51, "schedule" },
	{ 0xe914e41e, "strcpy" },
	{ 0x23269a13, "strict_strtoul" },
	{ 0x9d669763, "memcpy" },
	{ 0x657d0a00, "init_net" },
	{ 0x96d08782, "netlink_kernel_create" },
	{ 0x61651be, "strcat" },
	{ 0x3c2c5af5, "sprintf" },
	{ 0x8d8355b7, "sock_release" },
	{ 0xea147363, "printk" },
};

static const char __module_depends[]
__used
__attribute__((section(".modinfo"))) =
"depends=";


MODULE_INFO(srcversion, "C1F7A64B1BD223591933FC0");
