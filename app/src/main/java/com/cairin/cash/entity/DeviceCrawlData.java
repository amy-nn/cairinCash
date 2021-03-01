package com.cairin.cash.entity;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DeviceCrawlData {


    /**
     * hardware : {"device_name":"xiaomi","brand":"xiaomi","sdk_version":"28","model":"Redmi 7","release":"9","serial_number":"","physical_size":"5.522099298414953"}
     * location :
     * storage : {"ram_total_size":"2955337728","ram_usable_size":"784789504","internal_storage_usable":"6364114944","internal_storage_total":"23785328640","memory_card_size":"23785328640","memory_card_size_use":"17421213696"}
     * general_data : {"gaid":"","and_id":"2d618bfb741e2688","phone_type":"1","mac":"18:01:F1:67:AC:80","locale_iso_3_language":"zho","locale_display_language":"中文","locale_iso_3_country":"CHN","imei":"867183042370995","phone_number":"+8613277492577","network_operator_name":"中国联通","network_type":"WIFI","time_zone_id":"GMT+08:00","language":"zh"}
     * other_data : {"root_jailbreak":"0","last_boot_time":"1612206694370","keyboard":"","simulator":"0","dbm":"-85"}
     * application : [{"app_name":"屏幕录制","package":"com.miui.screenrecorder","version_name":"1.8.0","version_code":80,"in_time":118259000,"up_time":1604581013773,"flags":818462276,"app_type":"0"},{"app_name":"Cit","package":"com.longcheer.cit.local","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814267973,"app_type":"1"},{"app_name":"com.android.cts.priv.ctsshim","package":"com.android.cts.priv.ctsshim","version_name":"8.1.0-4396705","version_code":27,"in_time":1230739200000,"up_time":1230739200000,"flags":-1198997951,"app_type":"1"},{"app_name":"传送门","package":"com.miui.contentextension","version_name":"2.4.2","version_code":10164,"in_time":1230739200000,"up_time":1611335275362,"flags":953695941,"app_type":"1"},{"app_name":"边角显示屏凹口","package":"com.android.internal.display.cutout.emulation.corner","version_name":"1.0","version_code":1,"in_time":1230739200000,"up_time":1230739200000,"flags":814267969,"app_type":"1"},{"app_name":"双显示屏凹口","package":"com.android.internal.display.cutout.emulation.double","version_name":"1.0","version_code":1,"in_time":1230739200000,"up_time":1230739200000,"flags":814267969,"app_type":"1"},{"app_name":"电话和短信存储","package":"com.android.providers.telephony","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":1015791109,"app_type":"1"},{"app_name":"电量和性能","package":"com.miui.powerkeeper","version_name":"4.2.00","version_code":40200,"in_time":1230739200000,"up_time":1230739200000,"flags":952745541,"app_type":"1"},{"app_name":"GFManager","package":"com.goodix.fingerprint","version_name":"1.0.04","version_code":4,"in_time":1230739200000,"up_time":1230739200000,"flags":814235213,"app_type":"1"},{"app_name":"投屏服务","package":"com.xiaomi.miplay_client","version_name":"0.4.18","version_code":118,"in_time":1230739200000,"up_time":1604581010838,"flags":952680133,"app_type":"1"},{"app_name":"收音机","package":"com.miui.fm","version_name":"1.0.470.1","version_code":471,"in_time":1230739200000,"up_time":1611890495523,"flags":952680165,"app_type":"1"},{"app_name":"Cit_QR","package":"com.miui.qr","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814267973,"app_type":"1"},{"app_name":"日历存储","package":"com.android.providers.calendar","version_name":"10.0.3.0","version_code":10000300,"in_time":1230739200000,"up_time":1230739200000,"flags":948485701,"app_type":"1"},{"app_name":"应用程序扩展服务","package":"com.miui.contentcatcher","version_name":"1.0.001","version_code":1,"in_time":1230739200000,"up_time":1230739200000,"flags":818429517,"app_type":"1"},{"app_name":"媒体存储设备","package":"com.android.providers.media","version_name":"9","version_code":901,"in_time":1230739200000,"up_time":1230739200000,"flags":952647237,"app_type":"1"},{"app_name":"投屏","package":"com.milink.service","version_name":"12.4.1.2","version_code":12040102,"in_time":1230739200000,"up_time":1610400289985,"flags":814268101,"app_type":"1"},{"app_name":"RawDataTest","package":"com.goodix.rawdata","version_name":"1.8.0.12","version_code":1,"in_time":1230739200000,"up_time":1230739200000,"flags":948452933,"app_type":"1"},{"app_name":"爱奇艺","package":"com.qiyi.video","version_name":"12.1.5","version_code":800120155,"in_time":118254000,"up_time":1612459960553,"flags":953695812,"app_type":"0"},{"app_name":"耗电检测","package":"com.xiaomi.powerchecker","version_name":"2.0.00","version_code":20000,"in_time":1230739200000,"up_time":1230739200000,"flags":952647237,"app_type":"1"},{"app_name":"com.xiaomi.helper","package":"com.mobiletools.systemhelper","version_name":"2.1-build20190314","version_code":5,"in_time":1230739200000,"up_time":1230739200000,"flags":948485701,"app_type":"1"},{"app_name":"小米帐号","package":"com.xiaomi.account","version_name":"12.0.0.6","version_code":12000006,"in_time":1230739200000,"up_time":1604581068911,"flags":818429573,"app_type":"1"},{"app_name":"com.android.wallpapercropper","package":"com.android.wallpapercropper","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":815316549,"app_type":"1"},{"app_name":"com.quicinc.cne.CNEService.CNEServiceApp","package":"com.quicinc.cne.CNEService","version_name":"1.1","version_code":1,"in_time":1230739200000,"up_time":1230739200000,"flags":948485709,"app_type":"1"},{"app_name":"小米互联通信服务","package":"com.xiaomi.mi_connect_service","version_name":"2.0.193","version_code":16,"in_time":1230739200000,"up_time":1609941454469,"flags":952647365,"app_type":"1"},{"app_name":"com.qualcomm.qti.autoregistration","package":"com.qualcomm.qti.autoregistration","version_name":"3.0","version_code":3,"in_time":1230739200000,"up_time":1230739200000,"flags":948485701,"app_type":"1"},{"app_name":"com.xiaomi.micloudsdk.SdkApplication","package":"com.xiaomi.micloud.sdk","version_name":"1.0.0.0","version_code":10,"in_time":1230739200000,"up_time":1230739200000,"flags":-1333215675,"app_type":"1"},{"app_name":"应用包管理组件","package":"com.miui.packageinstaller","version_name":"2.0.0","version_code":200,"in_time":1230739200000,"up_time":1606270694100,"flags":818429637,"app_type":"1"},{"app_name":"手机营业厅","package":"com.sinovatech.unicom.ui","version_name":"8.1.2","version_code":101,"in_time":1609330880230,"up_time":1611945614539,"flags":953695812,"app_type":"0"},{"app_name":"系统更新","package":"com.android.updater","version_name":"6.6.2","version_code":73,"in_time":1230739200000,"up_time":1230739200000,"flags":952680005,"app_type":"1"},{"app_name":"文件","package":"com.android.documentsui","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":818527813,"app_type":"1"},{"app_name":"外部存储设备","package":"com.android.externalstorage","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814267973,"app_type":"1"},{"app_name":"com.qualcomm.uimremoteclient","package":"com.qualcomm.uimremoteclient","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814267973,"app_type":"1"},{"app_name":"游戏服务","package":"com.xiaomi.gamecenter.sdk.service","version_name":"6.9.1","version_code":6090100,"in_time":1230739200000,"up_time":1612789550405,"flags":415776453,"app_type":"1"},{"app_name":"HTML 查看器","package":"com.android.htmlviewer","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":952680005,"app_type":"1"},{"app_name":"Bokeh","package":"com.miui.extraphoto","version_name":"1.0.0","version_code":1,"in_time":1230739200000,"up_time":1230739200000,"flags":953728581,"app_type":"1"},{"app_name":"系统服务组件","package":"com.miui.securityadd","version_name":"9.10.80","version_code":91080,"in_time":1230739200000,"up_time":1612215176602,"flags":819478213,"app_type":"1"},{"app_name":"uceShimService","package":"com.qualcomm.qti.uceShimService","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":411614797,"app_type":"1"},{"app_name":"Companion Device Manager","package":"com.android.companiondevicemanager","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":818429509,"app_type":"1"},{"app_name":"相册","package":"com.miui.gallery","version_name":"2.2.17.19","version_code":266803,"in_time":1230739200000,"up_time":1612563633544,"flags":819576453,"app_type":"1"},{"app_name":"搜索","package":"com.android.quicksearchbox","version_name":"9.0.0.4","version_code":20201012,"in_time":1230739200000,"up_time":1613604926062,"flags":952745669,"app_type":"1"},{"app_name":"MmsService","package":"com.android.mms.service","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":948485701,"app_type":"1"},{"app_name":"com.qualcomm.qti.qms.service.connectionsecurity","package":"com.qualcomm.qti.qms.service.connectionsecurity","version_name":"935049","version_code":935049,"in_time":1230739200000,"up_time":1230739200000,"flags":814235205,"app_type":"1"},{"app_name":"下载管理程序","package":"com.android.providers.downloads","version_name":"9.09.16.800006","version_code":90916006,"in_time":1230739200000,"up_time":1230739200000,"flags":952680005,"app_type":"1"},{"app_name":"米币支付","package":"com.xiaomi.payment","version_name":"1.14.2","version_code":1049019,"in_time":1230739200000,"up_time":1610400483753,"flags":818429637,"app_type":"1"},{"app_name":"手机管家","package":"com.miui.securitycenter","version_name":"4.8.5","version_code":20201948,"in_time":1230739200000,"up_time":1612789541505,"flags":818527941,"app_type":"1"},{"app_name":"com.qualcomm.qti.telephonyservice","package":"com.qualcomm.qti.telephonyservice","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":948485709,"app_type":"1"},{"app_name":"浏览器","package":"com.android.browser","version_name":"12.8.25","version_code":120825000,"in_time":1230739200000,"up_time":1599713226262,"flags":953794245,"app_type":"1"},{"app_name":"智能服务","package":"com.miui.systemAdSolution","version_name":"2020.07.31.01-release","version_code":2020073101,"in_time":1230739200000,"up_time":1599951221104,"flags":415776453,"app_type":"1"},{"app_name":"FidoCryptoService","package":"com.qualcomm.qti.auth.fidocryptoservice","version_name":"1.0","version_code":1,"in_time":1230739200000,"up_time":1230739200000,"flags":814235205,"app_type":"1"},{"app_name":"com.qualcomm.qti.optinoverlay","package":"com.qualcomm.qti.optinoverlay","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":8928769,"app_type":"1"},{"app_name":"录音机","package":"com.android.soundrecorder","version_name":"1.9.43","version_code":90,"in_time":1230739200000,"up_time":1611945579888,"flags":818429573,"app_type":"1"},{"app_name":"百度输入法小米版","package":"com.baidu.input_mi","version_name":"8.2.4.211","version_code":540,"in_time":118257000,"up_time":1610400501781,"flags":952647236,"app_type":"0"},{"app_name":"软件包权限帮助程序","package":"com.android.defcontainer","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814235205,"app_type":"1"},{"app_name":"Conference URI Dialer","package":"com.qti.confuridialer","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814267973,"app_type":"1"},{"app_name":"MIUI安全组件","package":"com.miui.guardprovider","version_name":"1.0.8","version_code":108,"in_time":1230739200000,"up_time":1230739200000,"flags":814267973,"app_type":"1"},{"app_name":"下载管理","package":"com.android.providers.downloads.ui","version_name":"20.12.10.805001","version_code":201210501,"in_time":1230739200000,"up_time":1612477546109,"flags":952647365,"app_type":"1"},{"app_name":"PacProcessor","package":"com.android.pacprocessor","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814267973,"app_type":"1"},{"app_name":"Sim App Dialog","package":"com.android.simappdialog","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814267973,"app_type":"1"},{"app_name":"备份","package":"com.miui.backup","version_name":"6.3.4.2","version_code":6342,"in_time":1230739200000,"up_time":1230739200000,"flags":952680005,"app_type":"1"},{"app_name":"com.android.settings.overlay.cmcc","package":"com.android.settings.overlay.cmcc","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":8928769,"app_type":"1"},{"app_name":"通知管理","package":"com.miui.notification","version_name":"1.0.0.40","version_code":1000040,"in_time":1230739200000,"up_time":1230739200000,"flags":818429541,"app_type":"1"},{"app_name":"MiCloudSync","package":"com.miui.micloudsync","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":818429509,"app_type":"1"},{"app_name":"微信","package":"com.tencent.mm","version_name":"8.0.0","version_code":1840,"in_time":1554642351972,"up_time":1611688316473,"flags":949501508,"app_type":"0"},{"app_name":"长型显示屏凹口","package":"com.android.internal.display.cutout.emulation.tall","version_name":"1.0","version_code":1,"in_time":1230739200000,"up_time":1230739200000,"flags":814267969,"app_type":"1"},{"app_name":"MiuiDaemon","package":"com.miui.daemon","version_name":"2.0","version_code":30,"in_time":1230739200000,"up_time":1230739200000,"flags":948485709,"app_type":"1"},{"app_name":"mab","package":"com.xiaomi.ab","version_name":"1.4.4","version_code":144,"in_time":1230739200000,"up_time":1230739200000,"flags":953728581,"app_type":"1"},{"app_name":"证书安装程序","package":"com.android.certinstaller","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814235205,"app_type":"1"},{"app_name":"com.android.carrierconfig","package":"com.android.carrierconfig","version_name":"1.0.0","version_code":1,"in_time":1230739200000,"up_time":1230739200000,"flags":814235205,"app_type":"1"},{"app_name":"Android Accessibility Suite","package":"com.google.android.marvin.talkback","version_name":"8.1.0.278818032","version_code":60104040,"in_time":1230739200000,"up_time":1230739200000,"flags":818658885,"app_type":"1"},{"app_name":"WAPI证书","package":"com.wapi.wapicertmanage","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814267973,"app_type":"1"},{"app_name":"com.android.mms.overlay.cmcc","package":"com.android.mms.overlay.cmcc","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":8928769,"app_type":"1"},{"app_name":"com.qti.qualcomm.datastatusnotification","package":"com.qti.qualcomm.datastatusnotification","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814267981,"app_type":"1"},{"app_name":"Android 系统","package":"android","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":818462217,"app_type":"1"},{"app_name":"com.android.systemui.notch.overlay","package":"com.android.systemui.notch.overlay","version_name":"1.0","version_code":1,"in_time":1230739200000,"up_time":1230739200000,"flags":8928769,"app_type":"1"},{"app_name":"通讯录与拨号","package":"com.android.contacts","version_name":"11.0.9.1","version_code":122,"in_time":1230739200000,"up_time":1604581212354,"flags":818527941,"app_type":"1"},{"app_name":"小米换机","package":"com.miui.huanji","version_name":"2.1.3","version_code":20013,"in_time":1604513792000,"up_time":1604581241427,"flags":820526660,"app_type":"0"},{"app_name":"com.qualcomm.qti.callfeaturessetting","package":"com.qualcomm.qti.callfeaturessetting","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":948485701,"app_type":"1"},{"app_name":"Wfd服务","package":"com.qualcomm.wfd.service","version_name":"2.0","version_code":2,"in_time":1230739200000,"up_time":1230739200000,"flags":948485701,"app_type":"1"},{"app_name":"快应用服务框架","package":"com.miui.hybrid","version_name":"1.8.1.2","version_code":10810201,"in_time":1230739200000,"up_time":1611597794932,"flags":-1329054075,"app_type":"1"},{"app_name":"MConnService","package":"com.miui.vsimcore","version_name":"1.0.1","version_code":101,"in_time":1230739200000,"up_time":1230739200000,"flags":952680005,"app_type":"1"},{"app_name":"MiWebView","package":"com.mi.webkit.core","version_name":"1.42_fallback","version_code":16787968,"in_time":1230739200000,"up_time":1230739200000,"flags":-1198997947,"app_type":"1"},{"app_name":"安全核心组件","package":"com.miui.securitycore","version_name":"22","version_code":22,"in_time":1230739200000,"up_time":1230739200000,"flags":819510853,"app_type":"1"},{"app_name":"android.ui.overlay.ct","package":"android.ui.overlay.ct","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":8928769,"app_type":"1"},{"app_name":"设备信息","package":"com.qti.qualcomm.deviceinfo","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814267973,"app_type":"1"},{"app_name":"短信","package":"com.android.mms","version_name":"12.1.0.66","version_code":12010066,"in_time":1230739200000,"up_time":1595530081757,"flags":952745701,"app_type":"1"},{"app_name":"MTP 主机","package":"com.android.mtp","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814267973,"app_type":"1"},{"app_name":"USIM卡应用","package":"com.android.stk","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814267973,"app_type":"1"},{"app_name":"com.android.backupconfirm","package":"com.android.backupconfirm","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814235141,"app_type":"1"},{"app_name":"Dirac控制台","package":"se.dirac.acs","version_name":"2.7.6","version_code":276000,"in_time":1230739200000,"up_time":1230739200000,"flags":952647245,"app_type":"1"},{"app_name":"小米SIM卡激活服务","package":"com.xiaomi.simactivate.service","version_name":"2.0.190927","version_code":2,"in_time":1230739200000,"up_time":1230739200000,"flags":818429445,"app_type":"1"},{"app_name":"小米画报","package":"com.mfashiongallery.emag","version_name":"M920090800-S","version_code":2020090800,"in_time":118251000,"up_time":1600099967132,"flags":953695812,"app_type":"0"},{"app_name":"指纹测试","package":"com.goodix.gftest","version_name":"1.1.02","version_code":12,"in_time":1230739200000,"up_time":1230739200000,"flags":948452933,"app_type":"1"},{"app_name":"音乐","package":"com.miui.player","version_name":"3.39.1.0","version_code":11090,"in_time":1230739200000,"up_time":1592549244283,"flags":818462437,"app_type":"1"},{"app_name":"服务与反馈","package":"com.miui.miservice","version_name":"12.5.6.0","version_code":12050600,"in_time":1230739200000,"up_time":1612377156851,"flags":818462405,"app_type":"1"},{"app_name":"开机引导","package":"com.android.provision","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":818462277,"app_type":"1"},{"app_name":"org.codeaurora.ims","package":"org.codeaurora.ims","version_name":"1.0","version_code":1,"in_time":1230739200000,"up_time":1230739200000,"flags":948485701,"app_type":"1"},{"app_name":"Intent Filter Verification Service","package":"com.android.statementservice","version_name":"1.0","version_code":1,"in_time":1230739200000,"up_time":1230739200000,"flags":814235205,"app_type":"1"},{"app_name":"android.overlay.target","package":"android.overlay.target","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":8928769,"app_type":"1"},{"app_name":"SysoptApplication","package":"com.miui.sysopt","version_name":"1.0","version_code":1,"in_time":1230739200000,"up_time":1230739200000,"flags":948485701,"app_type":"1"},{"app_name":"com.miui.internal.app.SystemApplication","package":"com.miui.system","version_name":"1.14.0.0","version_code":1140000,"in_time":1230739200000,"up_time":1230739200000,"flags":952680005,"app_type":"1"},{"app_name":"Settings Suggestions","package":"com.android.settings.intelligence","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":818462277,"app_type":"1"},{"app_name":"天天消星星","package":"com.xg01.ttxxx","version_name":"2.5.0","version_code":250,"in_time":1601640277746,"up_time":1601640277746,"flags":948485700,"app_type":"0"},{"app_name":"日历","package":"com.android.calendar","version_name":"12.3.0.5","version_code":12030005,"in_time":1230739200000,"up_time":1610610992238,"flags":952745669,"app_type":"1"},{"app_name":"深色","package":"com.android.systemui.theme.dark","version_name":"1.0","version_code":1,"in_time":1230739200000,"up_time":1230739200000,"flags":814267969,"app_type":"1"},{"app_name":"com.miui.translation.kingsoft","package":"com.miui.translation.kingsoft","version_name":"1.0","version_code":1,"in_time":1230739200000,"up_time":1230739200000,"flags":814235205,"app_type":"1"},{"app_name":"com.miui.catcherpatch.BaseApplication","package":"com.miui.catcherpatch","version_name":"20180613.01","version_code":1,"in_time":1230739200000,"up_time":1230739200000,"flags":814235205,"app_type":"1"},{"app_name":"全球上网","package":"com.miui.virtualsim","version_name":"5.9.0","version_code":590,"in_time":118254000,"up_time":1604581433394,"flags":952647236,"app_type":"0"},{"app_name":"com.miui.systemui.devices.overlay","package":"com.miui.systemui.devices.overlay","version_name":"1.0","version_code":1,"in_time":1230739200000,"up_time":1230739200000,"flags":8928769,"app_type":"1"},{"app_name":"指南针","package":"com.miui.compass","version_name":"9.2.8","version_code":35,"in_time":118253000,"up_time":1572636122204,"flags":954777156,"app_type":"0"},{"app_name":"com.qualcomm.qti.dynamicddsservice","package":"com.qualcomm.qti.dynamicddsservice","version_name":"1.0","version_code":1,"in_time":1230739200000,"up_time":1230739200000,"flags":814267973,"app_type":"1"},{"app_name":"com.miui.rom","package":"com.miui.rom","version_name":"1.11.0.0","version_code":1110000,"in_time":1230739200000,"up_time":1230739200000,"flags":814267913,"app_type":"1"},{"app_name":"小米云盘","package":"com.android.midrive","version_name":"1.0.10","version_code":110,"in_time":118251000,"up_time":118251000,"flags":821608004,"app_type":"0"},{"app_name":"com.longcheer.autoregistration","package":"com.longcheer.autoregistration","version_name":"autoregistration.2017.09.25","version_code":1,"in_time":1230739200000,"up_time":1230739200000,"flags":814267973,"app_type":"1"},{"app_name":"com.qualcomm.qcrilmsgtunnel","package":"com.qualcomm.qcrilmsgtunnel","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":948485701,"app_type":"1"},{"app_name":"设置存储","package":"com.android.providers.settings","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814398981,"app_type":"1"},{"app_name":"MiuiVpnSdkManager","package":"com.miui.vpnsdkmanager","version_name":"3.0","version_code":3,"in_time":1230739200000,"up_time":1230739200000,"flags":818462277,"app_type":"1"},{"app_name":"com.android.sharedstoragebackup","package":"com.android.sharedstoragebackup","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814333445,"app_type":"1"},{"app_name":"融合位置服务","package":"com.xiaomi.location.fused","version_name":"1.2.22","version_code":11,"in_time":1230739200000,"up_time":1230739200000,"flags":814235205,"app_type":"1"},{"app_name":"打印处理服务","package":"com.android.printspooler","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":952647237,"app_type":"1"},{"app_name":"智能助理","package":"com.miui.personalassistant","version_name":"4.2.4214","version_code":4214,"in_time":1230739200000,"up_time":1601405237868,"flags":550092485,"app_type":"1"},{"app_name":"耳机和音效","package":"com.miui.misound","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":818462277,"app_type":"1"},{"app_name":"基本互动屏保","package":"com.android.dreams.basic","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814267973,"app_type":"1"},{"app_name":"电话","package":"com.android.incallui","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":818462277,"app_type":"1"},{"app_name":"com.android.systemui.gesture.line.overlay","package":"com.android.systemui.gesture.line.overlay","version_name":"1.0","version_code":1,"in_time":1230739200000,"up_time":1230739200000,"flags":8928769,"app_type":"1"},{"app_name":"FIDO UAF1.0 Client","package":"com.fido.xiaomi.uafclient","version_name":"3.3.0","version_code":3,"in_time":1230739200000,"up_time":1230739200000,"flags":948485701,"app_type":"1"},{"app_name":"用户反馈","package":"com.miui.bugreport","version_name":"3.2.4","version_code":30204,"in_time":1230739200000,"up_time":1610611019316,"flags":952680133,"app_type":"1"},{"app_name":"SecureElementApplication","package":"com.android.se","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814267981,"app_type":"1"},{"app_name":"输入设备","package":"com.android.inputdevices","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814267909,"app_type":"1"},{"app_name":"FIDO UAF1.0 ASM","package":"com.fido.asm","version_name":"3.1.6","version_code":3,"in_time":1230739200000,"up_time":1230739200000,"flags":948485701,"app_type":"1"},{"app_name":"系统打印服务","package":"com.android.bips","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":818462277,"app_type":"1"},{"app_name":"com.qti.dpmserviceapp","package":"com.qti.dpmserviceapp","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814267981,"app_type":"1"},{"app_name":"FingerprintExtensionService","package":"com.fingerprints.extension.service","version_name":"1.92","version_code":192,"in_time":1230739200000,"up_time":1230739200000,"flags":814235205,"app_type":"1"},{"app_name":"文件管理","package":"com.android.fileexplorer","version_name":"4.1.7.1","version_code":4171,"in_time":1230739200000,"up_time":1604581528100,"flags":819478213,"app_type":"1"},{"app_name":"X-Divert设置","package":"com.qti.xdivert","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814267973,"app_type":"1"},{"app_name":"com.android.systemui.overlay.cmcc","package":"com.android.systemui.overlay.cmcc","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":8928769,"app_type":"1"},{"app_name":"mircs","package":"com.xiaomi.mircs","version_name":"10.0.0.1","version_code":10001,"in_time":1230739200000,"up_time":1230739200000,"flags":948485709,"app_type":"1"},{"app_name":"com.longcheertel.smsregister","package":"com.longcheertel.smsregister","version_name":"smsRegister.2017.09.12","version_code":1,"in_time":1230739200000,"up_time":1230739200000,"flags":948485709,"app_type":"1"},{"app_name":"com.miui.translation.youdao","package":"com.miui.translation.youdao","version_name":"1.0","version_code":1,"in_time":1230739200000,"up_time":1230739200000,"flags":814235205,"app_type":"1"},{"app_name":"桌面云备份","package":"com.miui.cloudbackup","version_name":"12.0.0.21","version_code":12000021,"in_time":1230739200000,"up_time":1610117109772,"flags":819478213,"app_type":"1"},{"app_name":"Android System WebView","package":"com.google.android.webview","version_name":"80.0.3987.99","version_code":398709930,"in_time":1230739200000,"up_time":1230739200000,"flags":-1333215675,"app_type":"1"},{"app_name":"android.telephony.overlay.cmcc","package":"android.telephony.overlay.cmcc","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":8928769,"app_type":"1"},{"app_name":"Android Shared Library","package":"android.ext.shared","version_name":"1","version_code":1,"in_time":1230739200000,"up_time":1230739200000,"flags":814235205,"app_type":"1"},{"app_name":"One Time Init","package":"com.android.onetimeinitializer","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814267973,"app_type":"1"},{"app_name":"小米钱包","package":"com.mipay.wallet","version_name":"2.1.6","version_code":3030032,"in_time":1230739200000,"up_time":1230739200000,"flags":818429509,"app_type":"1"},{"app_name":"通话管理","package":"com.android.server.telecom","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":818429509,"app_type":"1"},{"app_name":"密钥链","package":"com.android.keychain","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814235205,"app_type":"1"},{"app_name":"相机","package":"com.android.camera","version_name":"3.0.200116","version_code":3,"in_time":1230739200000,"up_time":1230739200000,"flags":953794117,"app_type":"1"},{"app_name":"Print Service Recommendation Service","package":"com.android.printservice.recommendation","version_name":"1.3.0","version_code":4,"in_time":1230739200000,"up_time":1230739200000,"flags":814235141,"app_type":"1"},{"app_name":"小米卡包","package":"com.xiaomi.pass","version_name":"2.3.15","version_code":20315,"in_time":118258000,"up_time":1592325697865,"flags":950582852,"app_type":"0"},{"app_name":"UpnpService","package":"com.xiaomi.upnp","version_name":"1.0.0","version_code":1,"in_time":1230739200000,"up_time":1230739200000,"flags":814267973,"app_type":"1"},{"app_name":"小米服务框架","package":"com.xiaomi.xmsf","version_name":"4.6.2","version_code":40006002,"in_time":1230739200000,"up_time":1611604388353,"flags":952647301,"app_type":"1"},{"app_name":"抖音短视频","package":"com.ss.android.ugc.aweme","version_name":"12.8.0","version_code":120801,"in_time":1577887493371,"up_time":1600349023677,"flags":951598660,"app_type":"0"},{"app_name":"Android Services Library","package":"android.ext.services","version_name":"1","version_code":1,"in_time":1230739200000,"up_time":1230739200000,"flags":814267973,"app_type":"1"},{"app_name":"com.qualcomm.qti.qtisystemservice","package":"com.qualcomm.qti.qtisystemservice","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814267973,"app_type":"1"},{"app_name":"Call Log Backup/Restore","package":"com.android.calllogbackup","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814333509,"app_type":"1"},{"app_name":"freeform","package":"com.miui.freeform","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":818462277,"app_type":"1"},{"app_name":"Xiaomi Service Framework Keeper","package":"com.xiaomi.xmsfkeeper","version_name":"1.0.1","version_code":10001,"in_time":1230739200000,"up_time":1230739200000,"flags":814267981,"app_type":"1"},{"app_name":"运营商默认应用","package":"com.android.carrierdefaultapp","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":948485701,"app_type":"1"},{"app_name":"com.qualcomm.qti.remoteSimlockAuth","package":"com.qualcomm.qti.remoteSimlockAuth","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814267973,"app_type":"1"},{"app_name":"儿歌多多","package":"com.duoduo.child.story","version_name":"4.8.6.0","version_code":4860,"in_time":1592880881738,"up_time":1600631766861,"flags":949501508,"app_type":"0"},{"app_name":"查找手机","package":"com.xiaomi.finddevice","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":818429453,"app_type":"1"},{"app_name":"ProxyHandler","package":"com.android.proxyhandler","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814267973,"app_type":"1"},{"app_name":"Joyose","package":"com.xiaomi.joyose","version_name":"2.1.36","version_code":136,"in_time":1230739200000,"up_time":1230739200000,"flags":818462277,"app_type":"1"},{"app_name":"内容中心","package":"com.miui.newhome","version_name":"3.4.2.3","version_code":10376,"in_time":1230739200000,"up_time":1612789562504,"flags":954744517,"app_type":"1"},{"app_name":"便签","package":"com.miui.notes","version_name":"2.3.7","version_code":237,"in_time":118251000,"up_time":1604762280193,"flags":818527812,"app_type":"0"},{"app_name":"小米视频","package":"com.miui.video","version_name":"v2020070390(MiVideo-UN)","version_code":2020070390,"in_time":1230739200000,"up_time":1593773055844,"flags":819510981,"app_type":"1"},{"app_name":"WMService","package":"com.miui.wmsvc","version_name":"1.0.10","version_code":1001001,"in_time":1230739200000,"up_time":1608329882244,"flags":818429637,"app_type":"1"},{"app_name":"应用商店","package":"com.xiaomi.market","version_name":"21.01.25.2161","version_code":4002161,"in_time":1230739200000,"up_time":1612501000166,"flags":948453061,"app_type":"1"},{"app_name":"小米设置","package":"com.xiaomi.misettings","version_name":"2.8.7.0","version_code":200729010,"in_time":1230739200000,"up_time":1604581606451,"flags":952680133,"app_type":"1"},{"app_name":"com.miui.translationservice","package":"com.miui.translationservice","version_name":"1.0","version_code":1,"in_time":1230739200000,"up_time":1230739200000,"flags":814235205,"app_type":"1"},{"app_name":"小米云服务","package":"com.miui.cloudservice","version_name":"12.0.1.5","version_code":12000105,"in_time":1230739200000,"up_time":1608588427108,"flags":818429637,"app_type":"1"},{"app_name":"工作资料设置","package":"com.android.managedprovisioning","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":952679941,"app_type":"1"},{"app_name":"智慧生活","package":"com.miui.hybrid.accessory","version_name":"1.4.1","version_code":1040101,"in_time":1230739200000,"up_time":1589044857041,"flags":818462405,"app_type":"1"},{"app_name":"Sensor Test Tool","package":"com.fingerprints.sensortesttool","version_name":"3.48","version_code":348,"in_time":1230739200000,"up_time":1230739200000,"flags":948452933,"app_type":"1"},{"app_name":"SoterService","package":"com.tencent.soter.soterserver","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814267981,"app_type":"1"},{"app_name":"照片屏幕保护程序","package":"com.android.dreams.phototable","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":949534277,"app_type":"1"},{"app_name":"karaoke","package":"com.miui.audiomonitor","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":818462277,"app_type":"1"},{"app_name":"com.miui.translation.xmcloud","package":"com.miui.translation.xmcloud","version_name":"1.1","version_code":2,"in_time":1230739200000,"up_time":1230739200000,"flags":814235205,"app_type":"1"},{"app_name":"搜狗输入法小米版","package":"com.sohu.inputmethod.sogou.xiaomi","version_name":"9.4.21.2009092109","version_code":1110,"in_time":1230739200000,"up_time":1600800043159,"flags":952647365,"app_type":"1"},{"app_name":"悬浮球","package":"com.miui.touchassistant","version_name":"9.0.0.1","version_code":9000001,"in_time":1230739200000,"up_time":1230739200000,"flags":952745541,"app_type":"1"},{"app_name":"设置搜索","package":"com.xiaomi.providers.appindex","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814267973,"app_type":"1"},{"app_name":"com.android.smspush","package":"com.android.smspush","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814267973,"app_type":"1"},{"app_name":"com.android.networksettings.overlay.ct","package":"com.android.networksettings.overlay.ct","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":8928769,"app_type":"1"},{"app_name":"计算器","package":"com.miui.calculator","version_name":"12.0.8","version_code":30000038,"in_time":118254000,"up_time":1604683628991,"flags":818429508,"app_type":"0"},{"app_name":"Live Wallpaper Picker","package":"com.android.wallpaper.livepicker","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":818462277,"app_type":"1"},{"app_name":"CloudServiceSysbase","package":"com.miui.cloudservice.sysbase","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814235141,"app_type":"1"},{"app_name":"com.miui.miwallpaper","package":"com.miui.miwallpaper","version_name":"9","version_code":1000,"in_time":1230739200000,"up_time":1230739200000,"flags":814267973,"app_type":"1"},{"app_name":"小米安全键盘","package":"com.miui.securityinputmethod","version_name":"1.0.0","version_code":100,"in_time":1230739200000,"up_time":1230739200000,"flags":818429509,"app_type":"1"},{"app_name":"com.android.systemui.navigation.bar.overlay","package":"com.android.systemui.navigation.bar.overlay","version_name":"1.0","version_code":1,"in_time":1230739200000,"up_time":1230739200000,"flags":8928769,"app_type":"1"},{"app_name":"MIUI Bluetooth","package":"com.xiaomi.bluetooth","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":818462277,"app_type":"1"},{"app_name":"网络位置服务","package":"com.xiaomi.metoknlp","version_name":"3.2.22","version_code":29,"in_time":1230739200000,"up_time":1230739200000,"flags":948452933,"app_type":"1"},{"app_name":"售后自动化测试","package":"com.longcheertel.AutoTest","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814267973,"app_type":"1"},{"app_name":"垃圾清理","package":"com.miui.cleanmaster","version_name":"4.4.6","version_code":100446,"in_time":118251000,"up_time":1611584318795,"flags":819576388,"app_type":"0"},{"app_name":"存储空间管理器","package":"com.android.storagemanager","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":818429509,"app_type":"1"},{"app_name":"Analytics","package":"com.miui.analytics","version_name":"2.55.0","version_code":2020111600,"in_time":1230739200000,"up_time":1608191489855,"flags":814268037,"app_type":"1"},{"app_name":"Bookmark Provider","package":"com.android.bookmarkprovider","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814267973,"app_type":"1"},{"app_name":"设置","package":"com.android.settings","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":952745541,"app_type":"1"},{"app_name":"com.qualcomm.qti.ims","package":"com.qualcomm.qti.ims","version_name":"1.0","version_code":1,"in_time":1230739200000,"up_time":1230739200000,"flags":948485701,"app_type":"1"},{"app_name":"com.qualcomm.qti.lpa","package":"com.qualcomm.qti.lpa","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814267973,"app_type":"1"},{"app_name":"com.qualcomm.qti.uim","package":"com.qualcomm.qti.uim","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814267973,"app_type":"1"},{"app_name":"天气","package":"com.miui.weather2","version_name":"12.2.5.2","version_code":12020502,"in_time":118255000,"up_time":1595530478239,"flags":952745540,"app_type":"0"},{"app_name":"Cairin Cash","package":"com.cairin.cash","version_name":"1.0","version_code":1,"in_time":1613703313775,"up_time":1613719921048,"flags":818462534,"app_type":"0"},{"app_name":"LocationServices","package":"com.qualcomm.location","version_name":"1.0","version_code":1,"in_time":1230739200000,"up_time":1230739200000,"flags":948452933,"app_type":"1"},{"app_name":"扫一扫","package":"com.xiaomi.scanner","version_name":"12.12.04","version_code":121204,"in_time":118254000,"up_time":1607351819997,"flags":684244548,"app_type":"0"},{"app_name":"阅读","package":"com.duokan.reader","version_name":"6.3.0","version_code":630200911,"in_time":118256000,"up_time":1600349264294,"flags":951598596,"app_type":"0"},{"app_name":"com.android.cts.ctsshim","package":"com.android.cts.ctsshim","version_name":"8.1.0-4396705","version_code":27,"in_time":1230739200000,"up_time":1230739200000,"flags":948485697,"app_type":"1"},{"app_name":"com.android.systemui.overlay.ct","package":"com.android.systemui.overlay.ct","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":8928769,"app_type":"1"},{"app_name":"生活黄页","package":"com.miui.yellowpage","version_name":"20200303","version_code":20200303,"in_time":1230739200000,"up_time":1604762267181,"flags":818462405,"app_type":"1"},{"app_name":"调频收音机","package":"com.caf.fmradio","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814268005,"app_type":"1"},{"app_name":"com.qti.diagservices","package":"com.qti.diagservices","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814267973,"app_type":"1"},{"app_name":"com.miui.systemui.carriers.overlay","package":"com.miui.systemui.carriers.overlay","version_name":"1.0","version_code":1,"in_time":1230739200000,"up_time":1230739200000,"flags":8928769,"app_type":"1"},{"app_name":"com.miui.systemui.overlay.devices.android","package":"com.miui.systemui.overlay.devices.android","version_name":"1.0","version_code":1,"in_time":1230739200000,"up_time":1230739200000,"flags":8928769,"app_type":"1"},{"app_name":"com.xiaomi.bluetooth.overlay","package":"com.xiaomi.bluetooth.overlay","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":8928769,"app_type":"1"},{"app_name":"VpnDialogs","package":"com.android.vpndialogs","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814235205,"app_type":"1"},{"app_name":"工模测试","package":"com.longcheertel.cit","version_name":"1.4.3","version_code":143,"in_time":1230739200000,"up_time":1230739200000,"flags":814267973,"app_type":"1"},{"app_name":"电子邮件","package":"com.android.email","version_name":"V12_20200821_b4","version_code":903404,"in_time":118249000,"up_time":1600631854658,"flags":952745540,"app_type":"0"},{"app_name":"小爱同学","package":"com.miui.voiceassist","version_name":"4.10.4-201908221112","version_code":304010004,"in_time":1230739200000,"up_time":1230739200000,"flags":953728581,"app_type":"1"},{"app_name":"电话服务","package":"com.android.phone","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":952745549,"app_type":"1"},{"app_name":"Shell","package":"com.android.shell","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814267973,"app_type":"1"},{"app_name":"com.android.wallpaperbackup","package":"com.android.wallpaperbackup","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":881376773,"app_type":"1"},{"app_name":"存储已屏蔽的号码","package":"com.android.providers.blockednumber","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814267973,"app_type":"1"},{"app_name":"用户字典","package":"com.android.providers.userdictionary","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814267909,"app_type":"1"},{"app_name":"急救信息","package":"com.android.emergency","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":818462277,"app_type":"1"},{"app_name":"智能出行","package":"com.miui.smarttravel","version_name":"12.0.2.0","version_code":12000200,"in_time":1604513793000,"up_time":1604762330615,"flags":954744388,"app_type":"0"},{"app_name":"QMMI","package":"com.qualcomm.qti.qmmi","version_name":"4.0","version_code":400,"in_time":1230739200000,"up_time":1230739200000,"flags":952680005,"app_type":"1"},{"app_name":"一体化位置信息","package":"com.android.location.fused","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814267973,"app_type":"1"},{"app_name":"时钟","package":"com.android.deskclock","version_name":"12.6.8.1","version_code":12060801,"in_time":1230739200000,"up_time":1609172031049,"flags":952745669,"app_type":"1"},{"app_name":"系统 UI","package":"com.android.systemui","version_name":"20190625.0","version_code":201906250,"in_time":1230739200000,"up_time":1230739200000,"flags":819478029,"app_type":"1"},{"app_name":"Bluetooth MIDI Service","package":"com.android.bluetoothmidiservice","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814267973,"app_type":"1"},{"app_name":"配置拨号器","package":"com.qualcomm.qti.confdialer","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814267973,"app_type":"1"},{"app_name":"关机闹钟","package":"com.qualcomm.qti.poweroffalarm","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814267973,"app_type":"1"},{"app_name":"com.miui.smsextra.internal.SmsExtraApp","package":"com.miui.smsextra","version_name":"11.0.1.70","version_code":11000170,"in_time":1230739200000,"up_time":1604768170970,"flags":952680133,"app_type":"1"},{"app_name":"主题壁纸","package":"com.android.thememanager","version_name":"1.9.2.1","version_code":921,"in_time":1230739200000,"up_time":1611945628427,"flags":819576517,"app_type":"1"},{"app_name":"系统跟踪","package":"com.android.traceur","version_name":"1.0","version_code":2,"in_time":1230739200000,"up_time":1230739200000,"flags":948485701,"app_type":"1"},{"app_name":"收音机","package":"com.miui.fmservice","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814267973,"app_type":"1"},{"app_name":"miui.external.Application","package":"com.android.thememanager.module","version_name":"1.0","version_code":1,"in_time":1230739200000,"up_time":1230739200000,"flags":948485701,"app_type":"1"},{"app_name":"com.qualcomm.qti.qms.service.trustzoneaccess","package":"com.qualcomm.qti.qms.service.trustzoneaccess","version_name":"935051","version_code":935051,"in_time":1230739200000,"up_time":1230739200000,"flags":814267973,"app_type":"1"},{"app_name":"授权管理","package":"com.lbe.security.miui","version_name":"1.1.3","version_code":113,"in_time":1230739200000,"up_time":1230739200000,"flags":818462277,"app_type":"1"},{"app_name":"蓝牙","package":"com.android.bluetooth","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":952680005,"app_type":"1"},{"app_name":"com.qualcomm.timeservice","package":"com.qualcomm.timeservice","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814267973,"app_type":"1"},{"app_name":"驾车场景","package":"com.xiaomi.drivemode","version_name":"2.0.002","version_code":20002,"in_time":118250000,"up_time":1604768270360,"flags":954777156,"app_type":"0"},{"app_name":"com.qualcomm.embms","package":"com.qualcomm.embms","version_name":"1.0","version_code":1,"in_time":1230739200000,"up_time":1230739200000,"flags":948485701,"app_type":"1"},{"app_name":"联系人存储","package":"com.android.providers.contacts","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":814235205,"app_type":"1"},{"app_name":"CaptivePortalLogin","package":"com.android.captiveportallogin","version_name":"9","version_code":28,"in_time":1230739200000,"up_time":1230739200000,"flags":948485701,"app_type":"1"},{"app_name":"MIUI SDK","package":"com.miui.core","version_name":"1.19.0.0","version_code":1190099,"in_time":1230739200000,"up_time":1230739200000,"flags":948452869,"app_type":"1"},{"app_name":"MiuiBiometric","package":"com.miui.face","version_name":"1.0.0","version_code":1,"in_time":1230739200000,"up_time":1230739200000,"flags":814267981,"app_type":"1"},{"app_name":"系统桌面","package":"com.miui.home","version_name":"RELEASE-4.15.2.1515-07231008","version_code":41521515,"in_time":1230739200000,"up_time":1599951278557,"flags":953794245,"app_type":"1"},{"app_name":"com.miui.xman","package":"com.miui.xman","version_name":"1.1","version_code":2,"in_time":1230739200000,"up_time":1230739200000,"flags":818429765,"app_type":"1"},{"app_name":"com.miui.yman","package":"com.miui.yman","version_name":"1.0","version_code":1,"in_time":1230739200000,"up_time":1230739200000,"flags":818429509,"app_type":"1"},{"app_name":"AudioEffect","package":"com.miui.audioeffect","version_name":"1.4","version_code":10400,"in_time":1230739200000,"up_time":1230739200000,"flags":814235205,"app_type":"1"}]
     * network : {"current_wifi":{"bssid":"56:c5:1a:ff:5e:04","ssid":"\"王亚楠的iPhone\"","mac":"02:00:00:00:00:00","name":"王亚楠的iPhone"},"IP":"172.20.10.5","configured_wifi":[{"bssid":"56:c5:1a:ff:5e:04","ssid":"王亚楠的iPhone","mac":"56:c5:1a:ff:5e:04","name":"王亚楠的iPhone"},{"bssid":"c4:36:55:3c:e8:99","ssid":"!","mac":"c4:36:55:3c:e8:99","name":"!"},{"bssid":"1c:fa:68:b6:ce:92","ssid":"BeiJing_BigData_Office_Top","mac":"1c:fa:68:b6:ce:92","name":"BeiJing_BigData_Office_Top"},{"bssid":"dc:90:88:9e:34:98","ssid":"Misagei","mac":"dc:90:88:9e:34:98","name":"Misagei"},{"bssid":"ec:56:23:de:5c:e8","ssid":"nova 5","mac":"ec:56:23:de:5c:e8","name":"nova 5"},{"bssid":"34:96:72:b0:3c:37","ssid":"TP-LINK_3C37","mac":"34:96:72:b0:3c:37","name":"TP-LINK_3C37"},{"bssid":"e2:06:e6:f1:21:1d","ssid":"WIN-N6VUTILBNM5 7057","mac":"e2:06:e6:f1:21:1d","name":"WIN-N6VUTILBNM5 7057"},{"bssid":"36:78:d7:d2:b1:17","ssid":"GIONEE S10","mac":"36:78:d7:d2:b1:17","name":"GIONEE S10"},{"bssid":"bc:54:fc:f6:b2:75","ssid":"BeiJing_BigData_Office_ShiXun","mac":"bc:54:fc:f6:b2:75","name":"BeiJing_BigData_Office_ShiXun"}]}
     * battery_status : {"battery_pct":100,"is_usb_charge":1,"is_ac_charge":0,"is_charging":1}
     * audio_external : 35
     * audio_internal : 129
     * images_external : 23
     * images_internal : 20
     * video_external : 18
     * video_internal : 0
     * download_files : 8
     * contact_group : 0
     */

    private Hardware hardware;
    private String location;
    private Storage storage;
    private GeneralData general_data;
    private OtherData other_data;
    private Network network;
    private BatteryStatus battery_status;
    private String audio_external;
    private String audio_internal;
    private String images_external;
    private String images_internal;
    private String video_external;
    private String video_internal;
    private String download_files;
    private String contact_group;
    private List<Application> application;

    public static DeviceCrawlData objectFromData(String str) {

        return new Gson().fromJson(str, DeviceCrawlData.class);
    }

    public Hardware getHardware() {
        return hardware;
    }

    public void setHardware(Hardware hardware) {
        this.hardware = hardware;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public GeneralData getGeneral_data() {
        return general_data;
    }

    public void setGeneral_data(GeneralData general_data) {
        this.general_data = general_data;
    }

    public OtherData getOther_data() {
        return other_data;
    }

    public void setOther_data(OtherData other_data) {
        this.other_data = other_data;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public BatteryStatus getBattery_status() {
        return battery_status;
    }

    public void setBattery_status(BatteryStatus battery_status) {
        this.battery_status = battery_status;
    }

    public String getAudio_external() {
        return audio_external;
    }

    public void setAudio_external(String audio_external) {
        this.audio_external = audio_external;
    }

    public String getAudio_internal() {
        return audio_internal;
    }

    public void setAudio_internal(String audio_internal) {
        this.audio_internal = audio_internal;
    }

    public String getImages_external() {
        return images_external;
    }

    public void setImages_external(String images_external) {
        this.images_external = images_external;
    }

    public String getImages_internal() {
        return images_internal;
    }

    public void setImages_internal(String images_internal) {
        this.images_internal = images_internal;
    }

    public String getVideo_external() {
        return video_external;
    }

    public void setVideo_external(String video_external) {
        this.video_external = video_external;
    }

    public String getVideo_internal() {
        return video_internal;
    }

    public void setVideo_internal(String video_internal) {
        this.video_internal = video_internal;
    }

    public String getDownload_files() {
        return download_files;
    }

    public void setDownload_files(String download_files) {
        this.download_files = download_files;
    }

    public String getContact_group() {
        return contact_group;
    }

    public void setContact_group(String contact_group) {
        this.contact_group = contact_group;
    }

    public List<Application> getApplication() {
        return application;
    }

    public void setApplication(List<Application> application) {
        this.application = application;
    }

    public static class Hardware {
        /**
         * device_name : xiaomi
         * brand : xiaomi
         * sdk_version : 28
         * model : Redmi 7
         * release : 9
         * serial_number :
         * physical_size : 5.522099298414953
         */

        private String device_name;
        private String brand;
        private String sdk_version;
        private String model;
        private String release;
        private String serial_number;
        private String physical_size;

        public static Hardware objectFromData(String str) {

            return new Gson().fromJson(str, Hardware.class);
        }

        public String getDevice_name() {
            return device_name;
        }

        public void setDevice_name(String device_name) {
            this.device_name = device_name;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getSdk_version() {
            return sdk_version;
        }

        public void setSdk_version(String sdk_version) {
            this.sdk_version = sdk_version;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getRelease() {
            return release;
        }

        public void setRelease(String release) {
            this.release = release;
        }

        public String getSerial_number() {
            return serial_number;
        }

        public void setSerial_number(String serial_number) {
            this.serial_number = serial_number;
        }

        public String getPhysical_size() {
            return physical_size;
        }

        public void setPhysical_size(String physical_size) {
            this.physical_size = physical_size;
        }
    }

    public static class Storage {
        /**
         * ram_total_size : 2955337728
         * ram_usable_size : 784789504
         * internal_storage_usable : 6364114944
         * internal_storage_total : 23785328640
         * memory_card_size : 23785328640
         * memory_card_size_use : 17421213696
         */

        private String ram_total_size;
        private String ram_usable_size;
        private String internal_storage_usable;
        private String internal_storage_total;
        private String memory_card_size;
        private String memory_card_size_use;

        public static Storage objectFromData(String str) {

            return new Gson().fromJson(str, Storage.class);
        }

        public String getRam_total_size() {
            return ram_total_size;
        }

        public void setRam_total_size(String ram_total_size) {
            this.ram_total_size = ram_total_size;
        }

        public String getRam_usable_size() {
            return ram_usable_size;
        }

        public void setRam_usable_size(String ram_usable_size) {
            this.ram_usable_size = ram_usable_size;
        }

        public String getInternal_storage_usable() {
            return internal_storage_usable;
        }

        public void setInternal_storage_usable(String internal_storage_usable) {
            this.internal_storage_usable = internal_storage_usable;
        }

        public String getInternal_storage_total() {
            return internal_storage_total;
        }

        public void setInternal_storage_total(String internal_storage_total) {
            this.internal_storage_total = internal_storage_total;
        }

        public String getMemory_card_size() {
            return memory_card_size;
        }

        public void setMemory_card_size(String memory_card_size) {
            this.memory_card_size = memory_card_size;
        }

        public String getMemory_card_size_use() {
            return memory_card_size_use;
        }

        public void setMemory_card_size_use(String memory_card_size_use) {
            this.memory_card_size_use = memory_card_size_use;
        }
    }

    public static class GeneralData {
        /**
         * gaid :
         * and_id : 2d618bfb741e2688
         * phone_type : 1
         * mac : 18:01:F1:67:AC:80
         * locale_iso_3_language : zho
         * locale_display_language : 中文
         * locale_iso_3_country : CHN
         * imei : 867183042370995
         * phone_number : +8613277492577
         * network_operator_name : 中国联通
         * network_type : WIFI
         * time_zone_id : GMT+08:00
         * language : zh
         */

        private String gaid;
        private String and_id;
        private String phone_type;
        private String mac;
        private String locale_iso_3_language;
        private String locale_display_language;
        private String locale_iso_3_country;
        private String imei;
        private String phone_number;
        private String network_operator_name;
        private String network_type;
        private String time_zone_id;
        private String language;

        public static GeneralData objectFromData(String str) {

            return new Gson().fromJson(str, GeneralData.class);
        }

        public String getGaid() {
            return gaid;
        }

        public void setGaid(String gaid) {
            this.gaid = gaid;
        }

        public String getAnd_id() {
            return and_id;
        }

        public void setAnd_id(String and_id) {
            this.and_id = and_id;
        }

        public String getPhone_type() {
            return phone_type;
        }

        public void setPhone_type(String phone_type) {
            this.phone_type = phone_type;
        }

        public String getMac() {
            return mac;
        }

        public void setMac(String mac) {
            this.mac = mac;
        }

        public String getLocale_iso_3_language() {
            return locale_iso_3_language;
        }

        public void setLocale_iso_3_language(String locale_iso_3_language) {
            this.locale_iso_3_language = locale_iso_3_language;
        }

        public String getLocale_display_language() {
            return locale_display_language;
        }

        public void setLocale_display_language(String locale_display_language) {
            this.locale_display_language = locale_display_language;
        }

        public String getLocale_iso_3_country() {
            return locale_iso_3_country;
        }

        public void setLocale_iso_3_country(String locale_iso_3_country) {
            this.locale_iso_3_country = locale_iso_3_country;
        }

        public String getImei() {
            return imei;
        }

        public void setImei(String imei) {
            this.imei = imei;
        }

        public String getPhone_number() {
            return phone_number;
        }

        public void setPhone_number(String phone_number) {
            this.phone_number = phone_number;
        }

        public String getNetwork_operator_name() {
            return network_operator_name;
        }

        public void setNetwork_operator_name(String network_operator_name) {
            this.network_operator_name = network_operator_name;
        }

        public String getNetwork_type() {
            return network_type;
        }

        public void setNetwork_type(String network_type) {
            this.network_type = network_type;
        }

        public String getTime_zone_id() {
            return time_zone_id;
        }

        public void setTime_zone_id(String time_zone_id) {
            this.time_zone_id = time_zone_id;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }
    }

    public static class OtherData {
        /**
         * root_jailbreak : 0
         * last_boot_time : 1612206694370
         * keyboard :
         * simulator : 0
         * dbm : -85
         */

        private String root_jailbreak;
        private String last_boot_time;
        private String keyboard;
        private String simulator;
        private String dbm;

        public static OtherData objectFromData(String str) {

            return new Gson().fromJson(str, OtherData.class);
        }

        public String getRoot_jailbreak() {
            return root_jailbreak;
        }

        public void setRoot_jailbreak(String root_jailbreak) {
            this.root_jailbreak = root_jailbreak;
        }

        public String getLast_boot_time() {
            return last_boot_time;
        }

        public void setLast_boot_time(String last_boot_time) {
            this.last_boot_time = last_boot_time;
        }

        public String getKeyboard() {
            return keyboard;
        }

        public void setKeyboard(String keyboard) {
            this.keyboard = keyboard;
        }

        public String getSimulator() {
            return simulator;
        }

        public void setSimulator(String simulator) {
            this.simulator = simulator;
        }

        public String getDbm() {
            return dbm;
        }

        public void setDbm(String dbm) {
            this.dbm = dbm;
        }
    }

    public static class Network {
        /**
         * current_wifi : {"bssid":"56:c5:1a:ff:5e:04","ssid":"\"王亚楠的iPhone\"","mac":"02:00:00:00:00:00","name":"王亚楠的iPhone"}
         * IP : 172.20.10.5
         * configured_wifi : [{"bssid":"56:c5:1a:ff:5e:04","ssid":"王亚楠的iPhone","mac":"56:c5:1a:ff:5e:04","name":"王亚楠的iPhone"},{"bssid":"c4:36:55:3c:e8:99","ssid":"!","mac":"c4:36:55:3c:e8:99","name":"!"},{"bssid":"1c:fa:68:b6:ce:92","ssid":"BeiJing_BigData_Office_Top","mac":"1c:fa:68:b6:ce:92","name":"BeiJing_BigData_Office_Top"},{"bssid":"dc:90:88:9e:34:98","ssid":"Misagei","mac":"dc:90:88:9e:34:98","name":"Misagei"},{"bssid":"ec:56:23:de:5c:e8","ssid":"nova 5","mac":"ec:56:23:de:5c:e8","name":"nova 5"},{"bssid":"34:96:72:b0:3c:37","ssid":"TP-LINK_3C37","mac":"34:96:72:b0:3c:37","name":"TP-LINK_3C37"},{"bssid":"e2:06:e6:f1:21:1d","ssid":"WIN-N6VUTILBNM5 7057","mac":"e2:06:e6:f1:21:1d","name":"WIN-N6VUTILBNM5 7057"},{"bssid":"36:78:d7:d2:b1:17","ssid":"GIONEE S10","mac":"36:78:d7:d2:b1:17","name":"GIONEE S10"},{"bssid":"bc:54:fc:f6:b2:75","ssid":"BeiJing_BigData_Office_ShiXun","mac":"bc:54:fc:f6:b2:75","name":"BeiJing_BigData_Office_ShiXun"}]
         */

        private CurrentWifi current_wifi;
        private String IP;
        private List<ConfiguredWifi> configured_wifi;

        public static Network objectFromData(String str) {

            return new Gson().fromJson(str, Network.class);
        }

        public CurrentWifi getCurrent_wifi() {
            return current_wifi;
        }

        public void setCurrent_wifi(CurrentWifi current_wifi) {
            this.current_wifi = current_wifi;
        }

        public String getIP() {
            return IP;
        }

        public void setIP(String IP) {
            this.IP = IP;
        }

        public List<ConfiguredWifi> getConfigured_wifi() {
            return configured_wifi;
        }

        public void setConfigured_wifi(List<ConfiguredWifi> configured_wifi) {
            this.configured_wifi = configured_wifi;
        }

        public static class CurrentWifi {
            /**
             * bssid : 56:c5:1a:ff:5e:04
             * ssid : "王亚楠的iPhone"
             * mac : 02:00:00:00:00:00
             * name : 王亚楠的iPhone
             */

            private String bssid;
            private String ssid;
            private String mac;
            private String name;

            public static CurrentWifi objectFromData(String str) {

                return new Gson().fromJson(str, CurrentWifi.class);
            }

            public String getBssid() {
                return bssid;
            }

            public void setBssid(String bssid) {
                this.bssid = bssid;
            }

            public String getSsid() {
                return ssid;
            }

            public void setSsid(String ssid) {
                this.ssid = ssid;
            }

            public String getMac() {
                return mac;
            }

            public void setMac(String mac) {
                this.mac = mac;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class ConfiguredWifi {
            /**
             * bssid : 56:c5:1a:ff:5e:04
             * ssid : 王亚楠的iPhone
             * mac : 56:c5:1a:ff:5e:04
             * name : 王亚楠的iPhone
             */

            private String bssid;
            private String ssid;
            private String mac;
            private String name;

            public static ConfiguredWifi objectFromData(String str) {

                return new Gson().fromJson(str, ConfiguredWifi.class);
            }

            public String getBssid() {
                return bssid;
            }

            public void setBssid(String bssid) {
                this.bssid = bssid;
            }

            public String getSsid() {
                return ssid;
            }

            public void setSsid(String ssid) {
                this.ssid = ssid;
            }

            public String getMac() {
                return mac;
            }

            public void setMac(String mac) {
                this.mac = mac;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }

    public static class BatteryStatus {
        /**
         * battery_pct : 100
         * is_usb_charge : 1
         * is_ac_charge : 0
         * is_charging : 1
         */

        private int battery_pct;
        private int is_usb_charge;
        private int is_ac_charge;
        private int is_charging;

        public static BatteryStatus objectFromData(String str) {

            return new Gson().fromJson(str, BatteryStatus.class);
        }

        public int getBattery_pct() {
            return battery_pct;
        }

        public void setBattery_pct(int battery_pct) {
            this.battery_pct = battery_pct;
        }

        public int getIs_usb_charge() {
            return is_usb_charge;
        }

        public void setIs_usb_charge(int is_usb_charge) {
            this.is_usb_charge = is_usb_charge;
        }

        public int getIs_ac_charge() {
            return is_ac_charge;
        }

        public void setIs_ac_charge(int is_ac_charge) {
            this.is_ac_charge = is_ac_charge;
        }

        public int getIs_charging() {
            return is_charging;
        }

        public void setIs_charging(int is_charging) {
            this.is_charging = is_charging;
        }
    }

    public static class Application {
        /**
         * app_name : 屏幕录制
         * package : com.miui.screenrecorder
         * version_name : 1.8.0
         * version_code : 80
         * in_time : 118259000
         * up_time : 1604581013773
         * flags : 818462276
         * app_type : 0
         */

        private String app_name;
        @SerializedName("package")
        private String packageX;
        private String version_name;
        private int version_code;
        private long in_time;
        private long up_time;
        private int flags;
        private String app_type;

        public static Application objectFromData(String str) {

            return new Gson().fromJson(str, Application.class);
        }

        public String getApp_name() {
            return app_name;
        }

        public void setApp_name(String app_name) {
            this.app_name = app_name;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getVersion_name() {
            return version_name;
        }

        public void setVersion_name(String version_name) {
            this.version_name = version_name;
        }

        public int getVersion_code() {
            return version_code;
        }

        public void setVersion_code(int version_code) {
            this.version_code = version_code;
        }

        public long getIn_time() {
            return in_time;
        }

        public void setIn_time(long in_time) {
            this.in_time = in_time;
        }

        public long getUp_time() {
            return up_time;
        }

        public void setUp_time(long up_time) {
            this.up_time = up_time;
        }

        public int getFlags() {
            return flags;
        }

        public void setFlags(int flags) {
            this.flags = flags;
        }

        public String getApp_type() {
            return app_type;
        }

        public void setApp_type(String app_type) {
            this.app_type = app_type;
        }
    }
}
