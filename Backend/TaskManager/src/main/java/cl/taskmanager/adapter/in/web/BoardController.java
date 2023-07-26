package cl.taskmanager.adapter.in.web;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import cl.taskmanager.application.port.in.BoardManagerPort;
import cl.taskmanager.domain.Board;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/api", produces = "application/json")
public class BoardController {

    private final BoardManagerPort boardManagerPort;

    public BoardController(BoardManagerPort boardManagerPort) {
        this.boardManagerPort = boardManagerPort;
    }

    @Operation(summary = "Get all boards")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Boards found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = BoardCreation.class)) }),
    })
    @GetMapping(path = "/board")
    public ArrayList<Board> getBoards() {
        return this.boardManagerPort.getAll();
    }

    @Operation(summary = "Create new board")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Board created", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = BoardCreation.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid subject validation", content = @Content),
    })
    @PostMapping(path = "/board")
    public Board createNewBoard(@Validated @RequestBody BoardCreation create) {
        Board board = new Board();
        board.setSubject(create.getSubject());
        board.setDescription(create.getDescription());
        return this.boardManagerPort.create(board);
    }

    @Operation(summary = "Get board by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Board found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Board.class)) }),
            @ApiResponse(responseCode = "404", description = "Board not found", content = @Content),
    })
    @GetMapping(path = "/board/{id}")
    public Board getBoard(Long id) {
        try {
            return this.boardManagerPort.get(id);
        } catch (Exception exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID Not Found", exc);
        }
    }


}
