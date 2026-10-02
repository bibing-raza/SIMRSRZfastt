/*
 Navicat Premium Dump SQL

 Source Server         : DB Lokal
 Source Server Type    : MySQL
 Source Server Version : 100131 (10.1.31-MariaDB)
 Source Host           : localhost:3306
 Source Schema         : sik_laptop

 Target Server Type    : MySQL
 Target Server Version : 100131 (10.1.31-MariaDB)
 File Encoding         : 65001

 Date: 02/10/2026 16:06:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for verifikasi_cppt
-- ----------------------------
DROP TABLE IF EXISTS `verifikasi_cppt`;
CREATE TABLE `verifikasi_cppt`  (
  `no_rawat` varchar(17) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `nip_verifikator` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `waktu_simpan_cppt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `waktu_verif` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`waktu_verif`) USING BTREE,
  INDEX `no_rawat`(`no_rawat` ASC) USING BTREE,
  INDEX `nip_verifikator`(`nip_verifikator` ASC) USING BTREE,
  CONSTRAINT `verifikasi_cppt_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `verifikasi_cppt_ibfk_2` FOREIGN KEY (`nip_verifikator`) REFERENCES `pegawai` (`nik`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
