package com.dby.jweb.libs

/**
 * 定义Person对象
 */
class PersonBean {

  var id: Long? = null

  var name: String? = null

  /**
   * age属性不能为null
   */
  var age: Int = 0

  var address: String? = ""

  companion object {
    @JvmStatic
    val gender = 1

    @JvmStatic
    fun log() {
      println("gender: $gender, name: null")
    }
  }
}
