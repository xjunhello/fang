import java.text.SimpleDateFormat
import java.util.Date

object AppStart {
  def main(args: Array[String]): Unit = {
    var allMount: Double = 500000
    var period = 20
    var prePeriod = 1
    val sdf = new SimpleDateFormat("yyyy-MM-dd")
    var perMonth: Double = allMount / (period * 12)
    val lilv = 0.049
    //    var now = sdf.parse("2018-01-11")
    var lastMount = 0d
    var preMount = 200000d
    var tempPayMound=0d
    var allowPrePay = true
    var preType=2
    while (prePeriod < period*12 && allMount - tempPayMound >= preMount) {
      var paydMount = 0d
      var monthlixi = 0d
      var paydlixi = 0d
      var month = 1
      var realPeriod = 1
      while (paydMount < allMount) {
        if (prePeriod == month && allowPrePay == true) {
//          if(allMount - paydMount < preMount)
//            preMount = allMount - paydMount
          if(preType ==1 ){
            var predMonth: Int = preMount.toInt / perMonth.toInt
            println("减少还款期："+predMonth)
            paydMount += preMount
            month += predMonth.toInt
          }else if(preType==2){
            paydMount+=preMount
            val tempPerMonth = perMonth
            perMonth = ( allMount - paydMount ) / (period*12 - month -1)
            println("减少还款额从"+tempPerMonth.formatted("%.2f")+"到"+perMonth.formatted("%.2f"))
          }
//          tempPayMound = paydMount
          println("第" + prePeriod + "期，剩余本金："+(allMount-tempPayMound).formatted("%.2f")+"，提前还款：" + preMount.formatted("%.2f") + "，本金总计：" + paydMount.formatted("%.2f"))
        }
        if(paydMount<allMount){
          monthlixi = (allMount - paydMount) * lilv / 12
          paydlixi += monthlixi
          paydMount += perMonth
          //          println("第"+month+"期，还款："+(perMonth+monthlixi).formatted("%.2f")+"，本金总计："+paydMount.formatted("%.2f")+"，利息总计："+paydlixi.formatted("%.2f")+"，本金剩余："+(allMount-paydMount).formatted("%.2f"))
          month += 1
          realPeriod += 1
        }
        tempPayMound = paydMount

      }
      println("提前还款期：" + prePeriod + "，总还款期数：" + realPeriod + "，利息总计：" + paydlixi.formatted("%.2f"))
      prePeriod += 1
      tempPayMound = 0d
    }
    //    println("第×月提前还款×，总计还款：，总计利息：")
  }


}
