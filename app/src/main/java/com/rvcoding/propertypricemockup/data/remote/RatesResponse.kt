package com.rvcoding.propertypricemockup.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * Json to Kotlin converter used
 * */
@Serializable
data class RatesResponse(
    val success: Boolean,
    val timestamp: Long,
    val historical: Boolean,
    val base: String,
    val date: String,
    val rates: Rates,
)

@Serializable
data class Rates(
    @SerialName("AED")
    val aed: Double,
    @SerialName("AFN")
    val afn: Double,
    @SerialName("ALL")
    val all: Double,
    @SerialName("AMD")
    val amd: Double,
    @SerialName("ANG")
    val ang: Double,
    @SerialName("AOA")
    val aoa: Double,
    @SerialName("ARS")
    val ars: Double,
    @SerialName("AUD")
    val aud: Double,
    @SerialName("AWG")
    val awg: Double,
    @SerialName("AZN")
    val azn: Double,
    @SerialName("BAM")
    val bam: Double,
    @SerialName("BBD")
    val bbd: Double,
    @SerialName("BDT")
    val bdt: Double,
    @SerialName("BGN")
    val bgn: Double,
    @SerialName("BHD")
    val bhd: Double,
    @SerialName("BIF")
    val bif: Double,
    @SerialName("BMD")
    val bmd: Double,
    @SerialName("BND")
    val bnd: Double,
    @SerialName("BOB")
    val bob: Double,
    @SerialName("BRL")
    val brl: Double,
    @SerialName("BSD")
    val bsd: Double,
    @SerialName("BTC")
    val btc: Double,
    @SerialName("BTN")
    val btn: Double,
    @SerialName("BWP")
    val bwp: Double,
    @SerialName("BYN")
    val byn: Double,
    @SerialName("BYR")
    val byr: Double,
    @SerialName("BZD")
    val bzd: Double,
    @SerialName("CAD")
    val cad: Double,
    @SerialName("CDF")
    val cdf: Double,
    @SerialName("CHF")
    val chf: Double,
    @SerialName("CLF")
    val clf: Double,
    @SerialName("CLP")
    val clp: Double,
    @SerialName("CNY")
    val cny: Double,
    @SerialName("COP")
    val cop: Double,
    @SerialName("CRC")
    val crc: Double,
    @SerialName("CUC")
    val cuc: Double,
    @SerialName("CUP")
    val cup: Double,
    @SerialName("CVE")
    val cve: Double,
    @SerialName("CZK")
    val czk: Double,
    @SerialName("DJF")
    val djf: Double,
    @SerialName("DKK")
    val dkk: Double,
    @SerialName("DOP")
    val dop: Double,
    @SerialName("DZD")
    val dzd: Double,
    @SerialName("EGP")
    val egp: Double,
    @SerialName("ERN")
    val ern: Double,
    @SerialName("ETB")
    val etb: Double,
    @SerialName("EUR")
    val eur: Long,
    @SerialName("FJD")
    val fjd: Double,
    @SerialName("FKP")
    val fkp: Double,
    @SerialName("GBP")
    val gbp: Double,
    @SerialName("GEL")
    val gel: Double,
    @SerialName("GGP")
    val ggp: Double,
    @SerialName("GHS")
    val ghs: Double,
    @SerialName("GIP")
    val gip: Double,
    @SerialName("GMD")
    val gmd: Double,
    @SerialName("GNF")
    val gnf: Double,
    @SerialName("GTQ")
    val gtq: Double,
    @SerialName("GYD")
    val gyd: Double,
    @SerialName("HKD")
    val hkd: Double,
    @SerialName("HNL")
    val hnl: Double,
    @SerialName("HRK")
    val hrk: Double,
    @SerialName("HTG")
    val htg: Double,
    @SerialName("HUF")
    val huf: Double,
    @SerialName("IDR")
    val idr: Double,
    @SerialName("ILS")
    val ils: Double,
    @SerialName("IMP")
    val imp: Double,
    @SerialName("INR")
    val inr: Double,
    @SerialName("IQD")
    val iqd: Double,
    @SerialName("IRR")
    val irr: Double,
    @SerialName("ISK")
    val isk: Double,
    @SerialName("JEP")
    val jep: Double,
    @SerialName("JMD")
    val jmd: Double,
    @SerialName("JOD")
    val jod: Double,
    @SerialName("JPY")
    val jpy: Double,
    @SerialName("KES")
    val kes: Double,
    @SerialName("KGS")
    val kgs: Double,
    @SerialName("KHR")
    val khr: Double,
    @SerialName("KMF")
    val kmf: Double,
    @SerialName("KPW")
    val kpw: Double,
    @SerialName("KRW")
    val krw: Double,
    @SerialName("KWD")
    val kwd: Double,
    @SerialName("KYD")
    val kyd: Double,
    @SerialName("KZT")
    val kzt: Double,
    @SerialName("LAK")
    val lak: Double,
    @SerialName("LBP")
    val lbp: Double,
    @SerialName("LKR")
    val lkr: Double,
    @SerialName("LRD")
    val lrd: Double,
    @SerialName("LSL")
    val lsl: Double,
    @SerialName("LTL")
    val ltl: Double,
    @SerialName("LVL")
    val lvl: Double,
    @SerialName("LYD")
    val lyd: Double,
    @SerialName("MAD")
    val mad: Double,
    @SerialName("MDL")
    val mdl: Double,
    @SerialName("MGA")
    val mga: Double,
    @SerialName("MKD")
    val mkd: Double,
    @SerialName("MMK")
    val mmk: Double,
    @SerialName("MNT")
    val mnt: Double,
    @SerialName("MOP")
    val mop: Double,
    @SerialName("MRU")
    val mru: Double,
    @SerialName("MUR")
    val mur: Double,
    @SerialName("MVR")
    val mvr: Double,
    @SerialName("MWK")
    val mwk: Double,
    @SerialName("MXN")
    val mxn: Double,
    @SerialName("MYR")
    val myr: Double,
    @SerialName("MZN")
    val mzn: Double,
    @SerialName("NAD")
    val nad: Double,
    @SerialName("NGN")
    val ngn: Double,
    @SerialName("NIO")
    val nio: Double,
    @SerialName("NOK")
    val nok: Double,
    @SerialName("NPR")
    val npr: Double,
    @SerialName("NZD")
    val nzd: Double,
    @SerialName("OMR")
    val omr: Double,
    @SerialName("PAB")
    val pab: Double,
    @SerialName("PEN")
    val pen: Double,
    @SerialName("PGK")
    val pgk: Double,
    @SerialName("PHP")
    val php: Double,
    @SerialName("PKR")
    val pkr: Double,
    @SerialName("PLN")
    val pln: Double,
    @SerialName("PYG")
    val pyg: Double,
    @SerialName("QAR")
    val qar: Double,
    @SerialName("RON")
    val ron: Double,
    @SerialName("RSD")
    val rsd: Double,
    @SerialName("RUB")
    val rub: Double,
    @SerialName("RWF")
    val rwf: Double,
    @SerialName("SAR")
    val sar: Double,
    @SerialName("SBD")
    val sbd: Double,
    @SerialName("SCR")
    val scr: Double,
    @SerialName("SDG")
    val sdg: Double,
    @SerialName("SEK")
    val sek: Double,
    @SerialName("SGD")
    val sgd: Double,
    @SerialName("SHP")
    val shp: Double,
    @SerialName("SLE")
    val sle: Double,
    @SerialName("SLL")
    val sll: Double,
    @SerialName("SOS")
    val sos: Double,
    @SerialName("SRD")
    val srd: Double,
    @SerialName("STD")
    val std: Double,
    @SerialName("SVC")
    val svc: Double,
    @SerialName("SYP")
    val syp: Double,
    @SerialName("SZL")
    val szl: Double,
    @SerialName("THB")
    val thb: Double,
    @SerialName("TJS")
    val tjs: Double,
    @SerialName("TMT")
    val tmt: Double,
    @SerialName("TND")
    val tnd: Double,
    @SerialName("TOP")
    val top: Double,
    @SerialName("TRY")
    val try_field: Double,
    @SerialName("TTD")
    val ttd: Double,
    @SerialName("TWD")
    val twd: Double,
    @SerialName("TZS")
    val tzs: Double,
    @SerialName("UAH")
    val uah: Double,
    @SerialName("UGX")
    val ugx: Double,
    @SerialName("USD")
    val usd: Double,
    @SerialName("UYU")
    val uyu: Double,
    @SerialName("UZS")
    val uzs: Double,
    @SerialName("VEF")
    val vef: Double,
    @SerialName("VES")
    val ves: Double,
    @SerialName("VND")
    val vnd: Double,
    @SerialName("VUV")
    val vuv: Double,
    @SerialName("WST")
    val wst: Double,
    @SerialName("XAF")
    val xaf: Double,
    @SerialName("XAG")
    val xag: Double,
    @SerialName("XAU")
    val xau: Double,
    @SerialName("XCD")
    val xcd: Double,
    @SerialName("XDR")
    val xdr: Double,
    @SerialName("XOF")
    val xof: Double,
    @SerialName("XPF")
    val xpf: Double,
    @SerialName("YER")
    val yer: Double,
    @SerialName("ZAR")
    val zar: Double,
    @SerialName("ZMK")
    val zmk: Double,
    @SerialName("ZMW")
    val zmw: Double,
    @SerialName("ZWL")
    val zwl: Double,
)
