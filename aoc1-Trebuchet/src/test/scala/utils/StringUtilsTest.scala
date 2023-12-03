package utils

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers.{a, allOf, contain, convertToAnyMustWrapper, fullyMatch}
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

import scala.List
import scala.util.{Failure, Try}


class StringUtilsTest extends AnyFreeSpec {

  "getFirstAndLastChar" - {
    "Throws exception on empty String" in {
      Try(StringUtils.getFirstAndLastChar("")) mustBe a[Failure[_]]
    }
    "Gets the same Char from String with length 1" in {
      StringUtils.getFirstAndLastChar("a") shouldEqual ('a', 'a')
    }
    "Gets first and last Char from String with length > 1" in {
      StringUtils.getFirstAndLastChar("abc") shouldEqual ('a', 'c')
    }
  }

  "getSubstringsStartingFromEachIndex" - {
    "returns substrings starting from each index" in {
      StringUtils.getSubstringsStartingFromEachIndex("abcdef") should contain allOf ("abcdef", "bcdef", "cdef", "def", "ef", "f")
    }
  }

  "getDigits" - {
    "adds Digits to existing spelled digits" in {
      StringUtils.getDigits("two1nine") shouldBe "219"
      StringUtils.getDigits("eightwothree") shouldBe "823"
      StringUtils.getDigits("abcone2threexyz") shouldBe "123"
      StringUtils.getDigits("xtwone3four") shouldBe "2134"
      StringUtils.getDigits("4nineeightseven2") shouldBe "49872"
      StringUtils.getDigits("zoneight234") shouldBe "18234"
      StringUtils.getDigits("7pqrstsixteen") shouldBe "76"
    }
  }

}
