CREATE DATABASE  IF NOT EXISTS `project1` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `project1`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: project1
-- ------------------------------------------------------
-- Server version	5.1.32-community

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categorydb`
--

DROP TABLE IF EXISTS `categorydb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categorydb` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1 COMMENT='This table stores the mapping of category (string) to an uni';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorydb`
--

LOCK TABLES `categorydb` WRITE;
/*!40000 ALTER TABLE `categorydb` DISABLE KEYS */;
INSERT INTO `categorydb` VALUES (14,'Books & Reference'),(13,'Business'),(1,'Comics'),(2,'Communications'),(15,'Education'),(3,'Finance'),(23,'Games'),(4,'Health & Fitness'),(22,'Libraries & Demo'),(6,'Lifestyle'),(7,'Media & Video'),(5,'Medical'),(8,'Music & Audio'),(10,'News & Magazines'),(19,'Personalization'),(9,'Photography'),(12,'Productivity'),(16,'Shopping'),(17,'Social'),(18,'Sports'),(20,'Tools'),(21,'Travel & Local'),(24,'Unknown'),(11,'Weather');
/*!40000 ALTER TABLE `categorydb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `level_tab`
--

DROP TABLE IF EXISTS `level_tab`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `level_tab` (
  `level` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`level`),
  UNIQUE KEY `level_UNIQUE` (`level`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='maps a level number vs the level name';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `level_tab`
--

LOCK TABLES `level_tab` WRITE;
/*!40000 ALTER TABLE `level_tab` DISABLE KEYS */;
INSERT INTO `level_tab` VALUES (1,'Reserved'),(2,'Location'),(3,'Network/Wifi/Bluetooth'),(4,'Account'),(5,'sms/voicemail/call'),(6,'battery'),(7,'System Bind'),(8,'inter-app communication'),(9,'hardware'),(10,'other');
/*!40000 ALTER TABLE `level_tab` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `class_api`
--

DROP TABLE IF EXISTS `class_api`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `class_api` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(500) NOT NULL,
  `severity` int(11) NOT NULL,
  `keywords` varchar(100) NOT NULL,
  PRIMARY KEY (`id`,`name`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='used in parsing of applications....';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class_api`
--

LOCK TABLES `class_api` WRITE;
/*!40000 ALTER TABLE `class_api` DISABLE KEYS */;
INSERT INTO `class_api` VALUES (1,'android','Contains resource classes used by applications included in the platform and defines application permissions for system features.',3,'Resource classes'),(2,'android.accessibilityservice','The classes in this package are used for development of accessibility service that provide alternative or augmented feedback to the user.',1,'Accessibilty Service'),(3,'android.accounts','Handles accounts on devices',2,'Account Handling'),(4,'android.animation','These classes provide functionality for the property animation system, which allows you to animate object properties of any type.',1,'Animation'),(5,'android.app','Contains high-level classes encapsulating the overall Android application model.',1,'Android app model classes: admin , Backup , Restore'),(8,'android.appwidget','Contains the components necessary to create \"app widgets\", which users can embed in other applications (such as the home screen) to quickly access application data and services without launching a new activity.',1,'App widgets'),(9,'android.bluetooth','Provides classes that manage Bluetooth functionality, such as scanning for devices, connecting with devices, and managing data transfer between devices. The Bluetooth API supports both \"Classic Bluetooth\" and Bluetooth Low Energy.',3,'Bluetooth functionality'),(10,'android.content','Contains classes for accessing and publishing data on a device.',2,'Accessing & Publishing data on device'),(13,'android.database','Contains classes to explore data returned through a content provider.',2,'Content providers / SQLlite '),(15,'android.drm','Provides classes for managing DRM content and determining the capabilities of DRM plugins (agents).',2,'Other'),(16,'android.gesture','Provides classes to create, recognize, load and save gestures.',1,'Gesture recognition'),(17,'android.graphics','Provides low level graphics tools such as canvases, color filters, points, and rectangles that let you handle drawing to the screen directly.',1,'graphics tool'),(21,'android.hardware','Provides support for hardware features, such as the camera and other sensors. ',2,'manages h/w'),(22,'android.hardware.display','Manages hardware display',2,'manages h/w display'),(23,'android.hardware.usb','Provides support to communicate with USB hardware peripherals that are connected to Android-powered devices.',2,'manages USB'),(24,'android.inputmethodservice','Base classes for writing input methods (such as software keyboards). ',2,'manages i/p methods'),(25,'android.location','Contains the framework API classes that define Android location-based and related services.',2,'location based services'),(26,'android.media','Provides classes that manage various media interfaces in audio and video.',2,'media interfaces(audio/video)'),(27,'android.net','Classes that help with network access, beyond the normal java.net.* APIs.',3,'network access'),(28,'android.net.rtp','Provides APIs for RTP (Real-time Transport Protocol), allowing applications to manage on-demand or interactive data streaming. ',3,'network access(interactive)'),(29,'android.net.sip','Provides access to Session Initiation Protocol (SIP) functionality, such as making and answering VOIP calls using SIP.',3,'network access(SIP)'),(30,'android.net.wifi','Provides classes to manage Wi-Fi functionality on the device.',3,'WIFI funcitonality'),(31,'android.nfc','Provides access to Near Field Communication (NFC) functionality, allowing applications to read NDEF message in NFC tags.',3,'NFC functionality'),(32,'android.opengl','Provides an OpenGL ES static interface and utilities.',1,'OPENGL interfaces'),(33,'android.os','Provides basic operating system services, message passing, and inter-process communication on the device.',3,'Basic OS features'),(34,'android.os.storage','Contains classes for the system storage service, which manages binary asset filesystems known as Opaque Binary Blobs (OBBs).',2,'OS storage services'),(35,'android.preference','Provides classes that manage application preferences and implement the preferences UI.',1,'Manage preferences UI'),(36,'android.provider','Provides convenience classes to access the content providers supplied by Android.',2,'Access Content Providers'),(37,'android.renderscript','RenderScript provides support for high-performance computation across heterogeneous processors.',1,'High performance computation across heterogeneous systems'),(38,'android.security','Provides access to a few facilities of the Android security subsystems.',1,'Android Security subsystems'),(39,'android.telephony','Provides APIs for monitoring the basic phone information, such as the network type and connection state, plus utilities for manipulating phone number strings.',3,'Telephone / calls '),(40,'android.test','A framework for writing Android test cases and suites.',1,'Android Testing tools'),(41,'android.webkit','Provides tools for browsing the web.',1,'Web browsing tools');
/*!40000 ALTER TABLE `class_api` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mapdb`
--

DROP TABLE IF EXISTS `mapdb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mapdb` (
  `id` int(11) NOT NULL,
  `bitmap` blob NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mapdb`
--

LOCK TABLES `mapdb` WRITE;
/*!40000 ALTER TABLE `mapdb` DISABLE KEYS */;
INSERT INTO `mapdb` VALUES (8,'0000000000000000000001000010000110000010000000000000000000000000100000001000000000000001000001000000001000000000000000000000000000000001100000000000000010100000'),(9,'0000000000000000000001100011000100000000000010000000000000001000000000101000000000000001000001000000000010000000100000000000001000000000000000000000000001100100'),(12,'0000000000000000000001000010010110100000010000000000000010000000100000100000001010000000001001000000000010000000000000000000000010100000000000000000000010111110'),(17,'0000000000000000001010000011000110100000000000000000000010001010100110101000001000000000001001000000001010000000000000000000000000000000000000000000010000100110'),(24,'0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000');
/*!40000 ALTER TABLE `mapdb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissiondb`
--

DROP TABLE IF EXISTS `permissiondb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permissiondb` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `level` int(11) NOT NULL,
  `severity` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=145 DEFAULT CHARSET=latin1 COMMENT='This table maps permission (string) to an unique id(identifi';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissiondb`
--

LOCK TABLES `permissiondb` WRITE;
/*!40000 ALTER TABLE `permissiondb` DISABLE KEYS */;
INSERT INTO `permissiondb` VALUES (1,'ACCESS_CHECKIN_PROPERTIES','Allows read/write access to the properties table in the checkin database, to change values that get uploaded.',1,3),(2,'ACCESS_COARSE_LOCATION','Allows an app to access approximate location derived from network location sources such as cell towers and Wi-Fi.',3,2),(3,'ACCESS_FINE_LOCATION','Allows an app to access precise location from location sources such as GPS, cell towers, and Wi-Fi.',3,2),(4,'ACCESS_LOCATION_EXTRA_COMMAND','Allows an application to access extra location provider commands',2,2),(5,'ACCESS_MOCK_LOCATION','Allows an application to create mock location providers for testing',2,2),(6,'ACCESS_NETWORK_STATE','Allows applications to access information about networks',3,3),(7,'ACCESS_SURFACE_FLINGER','Allows an application to use SurfaceFlingers low level features.',1,3),(8,'ACCESS_WIFI_STATE','Allows applications to access information about Wi-Fi networks',3,3),(9,'ACCOUNT_MANAGER','Allows applications to call into AccountAuthenticators.',1,2),(10,'ADD_VOICEMAIL','Allows an application to add voicemails into the system.',5,3),(11,'AUTHENTICATE_ACCOUNTS','Allows an application to act as an AccountAuthenticator for the AccountManager',4,2),(12,'BATTERY_STATS','Allows an application to collect battery statistics',6,1),(13,'BIND_ACCESSIBILITY_SERVICE','Must be required by an AccessibilityService, to ensure that only the system can bind to it.',7,1),(14,'BIND_APPWIDGET','Allows an application to tell the AppWidget service which application can access AppWidgets data.',1,3),(15,'BIND_DEVICE_ADMIN','Must be required by device administration receiver, to ensure that only the system can interact with it.',7,1),(16,'BIND_INPUT_METHOD','Must be required by an InputMethodService, to ensure that only the system can bind to it.',7,1),(17,'BIND_NFC_SERVICE','Must be required by a HostApduService or OffHostApduService to ensure that only the system can bind to it.',7,1),(18,'BIND_NOTIFICATION_LISTENER_SERVICE','Must be required by an NotificationListenerService, to ensure that only the system can bind to it.',7,1),(19,'BIND_PRINT_SERVICE','Must be required by a PrintService, to ensure that only the system can bind to it.',7,1),(20,'BIND_REMOTEVIEWS','Must be required by a RemoteViewsService, to ensure that only the system can bind to it.',7,1),(21,'BIND_TEXT_SERVICE','Must be required by a TextService',7,1),(22,'BIND_VPN_SERVICE','Must be required by a VpnService, to ensure that only the system can bind to it.',7,1),(23,'BIND_WALLPAPER','Must be required by a WallpaperService, to ensure that only the system can bind to it.',7,1),(24,'BLUETOOTH','Allows applications to connect to paired bluetooth devices',3,3),(25,'BLUETOOTH_ADMIN','Allows applications to discover and pair bluetooth devices',3,3),(26,'BLUTOOTH_PRIVILEGED','Allows applications to pair bluetooth devices without user interaction.',3,3),(27,'BRICK','Required to be able to disable the device (very dangerous!).',1,3),(28,'BROADCAST_PACKAGE_REMOVED','Allows an application to broadcast a notification that an application package has been removed.',1,3),(29,'BROADCAST_SMS','Allows an application to broadcast an SMS receipt notification.',1,3),(30,'BROADCAST_STICKY','Allows an application to broadcast sticky intents.',8,1),(31,'BROADCAST_WAP_PUSH','Allows an application to broadcast a WAP PUSH receipt notification.',1,3),(32,'CALL_PHONE','Allows an application to initiate a phone call without going through the Dialer user interface for the user to confirm the call being placed.',5,3),(33,'CALL_PRIVILEGED','Allows an application to call any phone number, including emergency numbers, without going through the Dialer user interface for the user to confirm the call being placed.',1,3),(34,'CAMERA','Required to be able to access the camera device.',9,2),(35,'CAPTURE_AUDIO_OUTPUT','Allows an application to capture audio output.',1,3),(36,'CAPTURE_SECURE_VIDEO_OUTPUT','Allows an application to capture secure video output',1,3),(37,'CAPTURE_VIDEO_OUTPUT','Allows an application to capture video output.',1,3),(38,'CHANGE_COMPONENT_ENABLED_STATE','Allows an application to change whether an application component (other than its own) is enabled or not.',1,3),(39,'CHANGE_CONFIGURATION','Allows an application to modify the current configuration, such as locale.',10,1),(40,'CHANGE_NETWORK_STATE','Allows applications to change network connectivity state',3,3),(41,'CHANGE_WIFI_MULTICAST_STATE','Allows applications to enter Wi-Fi Multicast mode',3,3),(42,'CHANGE_WIFI_STATE','Allows applications to change Wi-Fi connectivity state',3,3),(43,'CLEAR_APP_CACHE','Allows an application to clear the caches of all installed applications on the device.',8,2),(44,'CLEAR_APP_USER_DATA','Allows an application to clear user data.',1,3),(45,'CONTROL_LOCATION_UPDATES','Allows enabling/disabling location update notifications from the radio.',1,3),(46,'DELETE_CACHE_FILES','Allows an application to delete cache files.',1,3),(47,'DELETE_PACKAGES','Allows an application to delete packages.',1,3),(48,'DEVICE_POWER','Allows low-level access to power management.',1,3),(49,'DIAGNOSTIC','Allows applications to RW to diagnostic resources.',1,3),(50,'DISABLE_KEYGAURD','Allows applications to disable the keyguard',10,1),(51,'DUMP','Allows an application to retrieve state dump information from system services.',1,3),(52,'EXPAND_STATUS_BAR','Allows an application to expand or collapse the status bar.',8,1),(53,'FACTORY_TEST','Run as a manufacturer test application, running as the root user.',1,3),(54,'FLASHLIGHT','Allows access to the flashlight',9,1),(55,'FORCE_BACK','Allows an application to force a BACK operation on whatever is the top activity.',1,3),(56,'GET_ACCOUNTS','Allows access to the list of accounts in the Accounts Service',4,2),(57,'GET_PACKAGE_SIZE','Allows an application to find out the space used by any package.',8,2),(58,'GET_TASKS','Allows an application to get information about the currently or recently running tasks.',8,2),(59,'GET_TOP_ACTIVITY_INFO','Allows an application to retrieve private information about the current top activity, such as any assist context it can provide.',1,3),(60,'GLOBAL_SEARCH','This permission can be used on content providers to allow the global search system to access their data.',1,3),(61,'HARDWARE_TEST','Allows access to hardware peripherals.',1,3),(62,'INJECT_EVENTS','Allows an application to inject user events (keys, touch, trackball) into the event stream and deliver them to ANY window.',1,3),(63,'INSTALL_LOCATION_PROVIDER','Allows an application to install a location provider into the Location Manager.',1,3),(64,'INSTALL_PACKAGES','Allows an application to install packages.',1,3),(65,'INSTALL_SHORTCUT','Allows an application to install a shortcut in Launcher',10,1),(66,'INTERNAL_SYSTEM_WINDOW','Allows an application to open windows that are for use by parts of the system user interface.',1,3),(67,'INTERNET','Allows applications to open network sockets.',3,3),(68,'KILL_BACKGROUND_PROCESSES','Allows an application to call killBackgroundProcesses(String).',8,3),(69,'LOCATION_HARDWARE','Allows an application to use location features in hardware, such as the geofencing api.',1,3),(70,'MANAGE_ACCOUNTS','Allows an application to manage the list of accounts in the AccountManager',4,2),(71,'MASTER_CLEAR','Not for use by third-party applications.',1,3),(72,'MEDIA_CONTENT_CONTROL','Allows an application to know what content is playing and control its playback.',1,3),(73,'MODIFY_AUDIO_SETTINGS','Allows an application to modify global audio settings',9,1),(74,'MODIFY_PHONE_STATE','Allows modification of the telephony state - power on, mmi, etc.',1,3),(75,'MOUNT_FORMAT_FILESYSTEMS','Allows formatting file systems for removable storage.',1,3),(76,'MOUNT_UNMOUNT_FILESYSTEMS','Allows mounting and unmounting file systems for removable storage.',1,3),(77,'NFC','Allows applications to perform I/O operations over NFC',9,2),(78,'PERSISTENT_ACTIVITY','This constant was deprecated in API level 9. This functionality will be removed in the future; please do not use. Allow an application to make its activities persistent.',10,1),(79,'PROCESS_OUTGOING_CALLS','Allows an application to capture secure video output',9,2),(80,'READ_CALENDER','Allows an application to read the users calendar data.',8,1),(81,'READ_CALL_LOG','Allows an application to read the users call log.',5,2),(82,'READ_CONTACTS','Allows an application to read the users contacts data.',5,2),(83,'READ_EXTERNAL_STORAGE','Allows an application to read from external storage.',9,2),(84,'READ_FRAME_BUFFER','Allows an application to take screen shots and more generally get access to the frame buffer data.',1,3),(85,'READ_HISTORY_BOOKMARKS','Allows an application to read (but not write) the user\'s browsing history and bookmarks.',8,2),(86,'READ_INPUT_STATE','This constant was deprecated in API level 16. The API that used this permission has been removed.',1,3),(87,'READ_LOGS','Allows an application to read the low-level system log files.',1,3),(88,'READ_PHONE_STATE','Allows read only access to phone state.',8,2),(89,'READ_PROFILE','Allows an application to read the user\'s personal profile data.',4,2),(90,'READ_SMS','Allows an application to read SMS messages.',5,2),(91,'READ_SOCIAL_STREAM','Allows an application to read from the user\'s social stream.',5,2),(92,'READ_SYNC_SETTINGS','Allows applications to read the sync settings',4,2),(93,'READ_SYNC_STATS','Allows applications to read the sync stats',4,2),(94,'READ_USER_DICTIONARY','Allows an application to read the user dictionary.',4,2),(95,'REBOOT','Required to be able to reboot the device.',1,3),(96,'RECEIVE_BOOT_COMPLETED','Allows an application to receive the ACTION_BOOT_COMPLETED that is broadcast after the system finishes booting.',8,1),(97,'RECEIVE_MMS','Allows an application to monitor incoming MMS messages, to record or perform processing on them.',5,2),(98,'RECEIVE_SMS','Allows an application to monitor incoming SMS messages, to record or perform processing on them.',5,2),(99,'RECEIVE_WAP_PUSH','Allows an application to monitor incoming WAP push messages.',5,2),(100,'RECORD_AUDIO','Allows an application to record audio',9,1),(101,'REORDER_TASKS','Allows an application to change the Z-order of tasks',8,1),(102,'RESTART_PACKAGES','This constant was deprecated in API level 8. The restartPackage(String) API is no longer supported.',8,1),(103,'SEND_RESPOND_VIA_MESSAGE','Allows an application (Phone) to send a request to other applications to handle the respond-via-message action during incoming calls.',1,3),(104,'SEND_SMS','Allows an application to send SMS messages.',5,3),(105,'SET_ACTIVITY_WATCHER','Allows an application to watch and control how activities are started globally in the system.',1,3),(106,'SET_ALARM','Allows an application to broadcast an Intent to set an alarm for the user.',8,1),(107,'SET_ALWAYS_FINISH','Allows an application to control whether activities are immediately finished when put in the background.',1,3),(108,'SET_ANIMATION_SCALE','Modify the global animation scaling factor.',1,3),(109,'SET_DEBUG_APP','Configure an application for debugging.',1,3),(110,'SET_ORIENTATION','Allows low-level access to setting the orientation (actually rotation) of the screen.',1,3),(111,'SET_POINTER_SPEED','Allows low-level access to setting the pointer speed.',1,3),(112,'SET_PREFERRED_APPLICATIONS','This constant was deprecated in API level 7. No longer useful, see addPackageToPreferred(String) for details.',10,1),(113,'SET_PROCESS_LIMIT','Allows an application to set the maximum number of (not needed) application processes that can be running.',1,3),(114,'SET_TIME','Allows applications to set the system time.',1,3),(115,'SET_TIME_ZONE','Allows applications to set the system time zone',8,1),(116,'SET_WALLPAPER','Allows applications to set the wallpaper',8,1),(117,'SET_WALLPAPER_HINTS','Allows applications to set the wallpaper hints',8,1),(118,'SIGNAL_PERSISTENT_PROCESSES','Allow an application to request that a signal be sent to all persistent processes.',1,3),(119,'STATUS_BAR','Allows an application to open, close, or disable the status bar and its icons.',1,3),(120,'SUBSCRIBED_FEEDS_READ','Allows an application to allow access the subscribed feeds ContentProvider.',8,1),(121,'SUBSCRIBED_FEEDS_WRITE','Allows an application to allow access the subscribed feeds ContentProvider.',8,1),(122,'SYSTEM_ALERT_WINDOW','Allows an application to open windows using the type TYPE_SYSTEM_ALERT, shown on top of all other applications.',8,1),(123,'TRANSMIT_IR','Allows using the devices IR transmitter, if available',9,2),(124,'UNINSTALL_SHORTCUT','Allows an application to uninstall a shortcut in Launcher',8,1),(125,'UPDATE_DEVICE_STATS','Allows an application to update device statistics.',1,3),(126,'USE_CREDENTIALS','Allows an application to request authtokens from the AccountManager',4,2),(127,'USE_SIP','Allows an application to use SIP service',1,3),(128,'VIBRATE','Allows access to the vibrator',9,2),(129,'WAKE_LOCK','Allows using PowerManager WakeLocks to keep processor from sleeping or screen from dimming',6,1),(130,'WRITE_APN_SETTINGS','Allows applications to write the apn settings.',0,1),(131,'WRITE_CALENDAR','Allows an application to write (but not read) the users calendar data',4,1),(132,'WRITE_CALL_LOG','Allows an application to write (but not read) the users calendar data',4,2),(133,'WRITE_CONTACTS','Allows an application to write (but not read) the users calendar data',4,2),(134,'WRITE_EXTERNAL_STORAGE','Allows an application to write to external storage.',9,2),(135,'WRITE_GSERVICES','Allows an application to modify the Google service map.',1,3),(136,'WRITE_HISTORY_BOOKMARKS','Allows an application to write (but not read) the users browsing history and bookmarks.',4,1),(137,'WRITE_PROFILE','Allows an application to write (but not read) the users personal profile data.',4,2),(138,'WRITE_SECURE_SETTINGS','Allows an application to read or write the secure system settings.',1,2),(139,'WRITE_SETTINGS','Allows an application to read or write the system settings.',4,2),(140,'WRITE_SMS','Allows an application to write SMS messages.',5,2),(141,'WRITE_SOCIAL_STREAM','Allows an application to write (but not read) the users social stream data.',5,2),(142,'WRITE_SYNC_SETTINGS','Allows applications to write the sync settings',4,2),(143,'WRITE_USER_DICTIONARY','Allows an application to write to the user dictionary.',4,2),(144,'DOWNLOAD_WITHOUT_NOTIFICATION','Allows an application to download data it requires.',3,3);
/*!40000 ALTER TABLE `permissiondb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'project1'
--

--
-- Dumping routines for database 'project1'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-04-04 23:42:38
