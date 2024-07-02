package org.ormi.stackorflow.infra.modules.discord;

import java.util.regex.Pattern;

final class DiscordMessageContentPatterns {

  private static final Pattern IMAGE_FILE_FORMAT_PATTERN =
      Pattern.compile("(\\S+(\\.(?i)(jpg|jpeg|png|gif|bmp|svg))$)");

  static boolean isImageFile(String filename) {
    return IMAGE_FILE_FORMAT_PATTERN.matcher(filename).matches();
  }
}
