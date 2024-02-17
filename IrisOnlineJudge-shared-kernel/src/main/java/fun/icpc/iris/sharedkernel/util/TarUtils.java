package fun.icpc.iris.sharedkernel.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * The utils of tar.
 */
public class TarUtils {

    /**
     * Build a tar input stream from entries.
     *
     * @param entries entries
     * @return tar input stream
     * @throws IOException io exception
     */
    public static InputStream buildTarInputStream(List<TarEntry> entries) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (TarArchiveOutputStream tarOut = new TarArchiveOutputStream(baos)) {

            for (TarEntry entry : entries) {
                TarArchiveEntry tarEntry = new TarArchiveEntry(entry.filename);
                byte[] contentBytes = entry.content.getBytes();
                tarEntry.setSize(contentBytes.length);
                tarOut.putArchiveEntry(tarEntry);
                tarOut.write(contentBytes);
                tarOut.closeArchiveEntry();
            }

            tarOut.finish();
        }

        return new ByteArrayInputStream(baos.toByteArray());
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class TarEntry {
        private String filename;
        private String content;
    }
}
