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

 Date: 02/10/2026 16:06:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cppt
-- ----------------------------
DROP TABLE IF EXISTS `cppt`;
CREATE TABLE `cppt`  (
  `no_rawat` varchar(17) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `tgl_cppt` date NULL DEFAULT NULL,
  `bagian` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `hasil_pemeriksaan` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `instruksi_nakes` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `verifikasi` enum('Belum','Sudah') CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `nip_dpjp` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `status` enum('Ralan','Ranap') CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `waktu_simpan` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `cek_jam` enum('ya','tidak') CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `jam_cppt` time NULL DEFAULT NULL,
  `jenis_ppa` enum('','Perawat','Bidan','Apoteker','Nutrisionis','Fisioterapis','Dokter IRNA','-','DPJP','DPJP Raber') CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `nip_ppa` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT '',
  `jenis_bagian` enum('','Dokter IGD','DPJP','PPA','-','DPJP (K)','DPJP Raber') CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT '',
  `serah_terima_cppt` enum('','Ya','Tidak','-') CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `nip_konsulen` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `nip_petugas_serah` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `nip_petugas_terima` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `cppt_shift` enum('-','1','2','3') CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT '-',
  `jam_serah_terima` time NULL DEFAULT NULL,
  `flag_hapus` enum('ya','tidak') CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `nip_penghapus` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `pilihan_soap` enum('Pilihan 1','Pilihan 2') CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `subjektif` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `objektif` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `asesmen` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `planing` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`waktu_simpan`) USING BTREE,
  INDEX `no_rawat`(`no_rawat` ASC) USING BTREE,
  INDEX `FK_cppt_dokter`(`nip_dpjp` ASC) USING BTREE,
  INDEX `nip_konsulen1`(`nip_konsulen` ASC) USING BTREE,
  INDEX `cppt_ibfk_2`(`nip_ppa` ASC) USING BTREE,
  INDEX `nip_petugas_serah`(`nip_petugas_serah` ASC) USING BTREE,
  INDEX `nip_petugas_terima`(`nip_petugas_terima` ASC) USING BTREE,
  INDEX `nip_penghapus`(`nip_penghapus` ASC) USING BTREE,
  INDEX `jenis_bagian`(`jenis_bagian` ASC) USING BTREE,
  INDEX `jenis_ppa`(`jenis_ppa` ASC) USING BTREE,
  INDEX `tgl_cppt`(`tgl_cppt` ASC) USING BTREE,
  CONSTRAINT `FK_cppt_dokter` FOREIGN KEY (`nip_dpjp`) REFERENCES `dokter` (`kd_dokter`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `cppt_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cppt_ibfk_2` FOREIGN KEY (`nip_ppa`) REFERENCES `petugas` (`nip`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `cppt_ibfk_3` FOREIGN KEY (`nip_konsulen`) REFERENCES `dokter` (`kd_dokter`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `cppt_ibfk_5` FOREIGN KEY (`nip_petugas_serah`) REFERENCES `petugas` (`nip`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `cppt_ibfk_6` FOREIGN KEY (`nip_petugas_terima`) REFERENCES `petugas` (`nip`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `cppt_ibfk_7` FOREIGN KEY (`nip_penghapus`) REFERENCES `petugas` (`nip`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
