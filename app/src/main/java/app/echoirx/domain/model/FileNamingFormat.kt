package app.echoirx.domain.model

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AudioFile
import androidx.compose.material.icons.outlined.MusicNote
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import app.echoirx.R

enum class FileNamingFormat(
    @param:StringRes val displayNameResId: Int,
    val format: (String, String, Int?) -> String,
    val icon: ImageVector
) {
    ARTIST_TITLE(
        R.string.file_format_artist_title_display,
        { artist, title, trackNumber ->
            val prefix =
                trackNumber?.let { String.format(java.util.Locale.getDefault(), "%02d. ", it) }
                    ?: ""
            "$prefix${artist.split(",").first().trim()} - $title"
        },
        Icons.Outlined.Person
    ),
    TITLE_ARTIST(
        R.string.file_format_title_artist_display,
        { artist, title, trackNumber ->
            val prefix =
                trackNumber?.let { String.format(java.util.Locale.getDefault(), "%02d. ", it) }
                    ?: ""
            "$prefix$title - ${artist.split(",").first().trim()}"
        },
        Icons.Outlined.MusicNote
    ),
    TITLE_ONLY(
        R.string.file_format_title_only_display,
        { _, title, trackNumber ->
            val prefix =
                trackNumber?.let { String.format(java.util.Locale.getDefault(), "%02d. ", it) }
                    ?: ""
            "$prefix$title"
        },
        Icons.Outlined.AudioFile
    );

    companion object {
        fun fromOrdinal(ordinal: Int) = entries.getOrNull(ordinal) ?: TITLE_ONLY
    }
}