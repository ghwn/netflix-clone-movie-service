package me.ghwn.netflix.movieservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RequestMapping("/api/v1/video-streams")
@RestController
public class VideoStreamController {

    private static final long CHUNK_SIZE = 1000000L;  // 1,000,000 bytes (1 MB)

    private final String videoDirectory;

    @Autowired
    public VideoStreamController(Environment environment) {
        this.videoDirectory = environment.getProperty("video.directory");
    }

    /**
     * Video streaming test
     */
    @GetMapping("/{filename}")
    public ResponseEntity<ResourceRegion> getMovieStream(@PathVariable String filename,
                                            @RequestHeader HttpHeaders headers) throws IOException {
        String moviePath = String.format("classpath:%s/%s.mp4", this.videoDirectory, filename);
        UrlResource movieResource = new UrlResource(moviePath);
        log.info("{} exists? {}", moviePath, movieResource.exists());
        ResourceRegion resourceRegion = getResourceRegion(movieResource, headers);
        return ResponseEntity
                .status(HttpStatus.PARTIAL_CONTENT)
                .contentType(MediaTypeFactory.getMediaType(movieResource).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(resourceRegion);
    }

    private ResourceRegion getResourceRegion(UrlResource resource, HttpHeaders headers) throws IOException {
        HttpRange httpRange = headers.getRange().stream().findFirst().orElse(null);
        long contentLength = resource.contentLength();
        if (httpRange != null) {
            long rangeStart = httpRange.getRangeStart(contentLength);
            long rangeEnd = httpRange.getRangeEnd(contentLength);
            long rangeLength = Long.min(CHUNK_SIZE, rangeEnd - rangeStart + 1);
            return new ResourceRegion(resource, rangeStart, rangeLength);
        } else {
            // If http-range is not specified, read full size resource
            long rangeLength = Long.min(CHUNK_SIZE, contentLength);
            return new ResourceRegion(resource, 0, rangeLength);
        }
    }

}
