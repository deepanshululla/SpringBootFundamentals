package ttl.larku.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ttl.larku.domain.Track;
import ttl.larku.service.TrackService;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/tracks")
public class TrackController {

    private TrackService trackService;
    private UriCreator uriCreator;

    //constructor injection
    public TrackController(TrackService trackService, UriCreator uriCreator) {
        this.trackService = trackService;
        this.uriCreator = uriCreator;
    }

    @GetMapping
    public ResponseEntity<?> getAllTracks() {
        List<Track> tracks = trackService.getAllTracks();
        return ResponseEntity.ok(tracks);
    }

    @GetMapping(value = "/{id:\\d+}")
    public ResponseEntity<?> getTrack(@PathVariable("id") int id) {
        Track track = trackService.getTrack(id);
        if (track == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(track);
    }

    @PostMapping
    public ResponseEntity<?> createTrack(@RequestBody Track track) {
        Track trackGen = trackService.createTrack(track);
        if (trackGen == null) {
            return ResponseEntity.badRequest().build();
        }
        URI uri = uriCreator.getUriFor(trackGen.getId());
        return ResponseEntity.created(uri).body(trackGen);
    }

    @DeleteMapping(value = "/{id:\\d+}")
    public ResponseEntity<?> deleteTrack(@PathVariable("id") int id) {
        Track track = trackService.getTrack(id);
        if (track == null) {
            return ResponseEntity.badRequest().build();
        }
        trackService.deleteTrack(id);
        return ResponseEntity.noContent().build();
    }

   @PutMapping
   public ResponseEntity<?> updateTrack(@RequestBody Track track) {
        Boolean response = trackService.updateTrack(track);
        if (!response) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.noContent().build();
   }

//    @GetMapping
//    public ResponseEntity<?> getAllTracks(@RequestParam Map<String, String> queryStrings) {
//        List<Track> tracks = null;
//        if(queryStrings.size() == 0) {
//            tracks = trackService.getAllTracks();
//        } else {
//            tracks = trackService.getAllTracks();
//        }
//        return ResponseEntity.ok(tracks);
//    }


}
