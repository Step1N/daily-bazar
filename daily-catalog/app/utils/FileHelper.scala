package utils

import java.nio.file.{Files, Paths}
import java.util.stream.Stream

class FileHelper {

  def readInputFile(fileName: String, fileType: String) = {
    val stream: Stream[String] = Files.lines(Paths.get(fileName))
    val numLines = stream.count
    stream.close
  }
}
