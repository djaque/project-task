package cl.taskmanager.application.service;

import java.util.ArrayList;

import cl.taskmanager.application.port.in.BoardManagerPort;
import cl.taskmanager.application.port.out.board.CreateBoardPort;
import cl.taskmanager.application.port.out.board.DeleteBoardPort;
import cl.taskmanager.application.port.out.board.GetBoardPort;
import cl.taskmanager.application.port.out.board.ListBoardPort;
import cl.taskmanager.application.port.out.board.UpdateBoardPort;
import cl.taskmanager.common.UseCase;
import cl.taskmanager.domain.Board;
import jakarta.transaction.Transactional;

@UseCase
public class BoardManagerService implements BoardManagerPort {

    private final ListBoardPort listBoardPort;
    private final CreateBoardPort createBoardPort;
    private final UpdateBoardPort updateBoardPort;
    private final DeleteBoardPort deleteBoardPort; 
    private final GetBoardPort getBoardPort;

    public BoardManagerService(
        ListBoardPort listBoardPort, 
        CreateBoardPort createBoardPort,
         UpdateBoardPort updateBoardPort, 
         DeleteBoardPort deleteBoardPort,
         GetBoardPort getBoardPort
    ) {
        this.listBoardPort = listBoardPort;
        this.createBoardPort = createBoardPort;
        this.updateBoardPort = updateBoardPort;
        this.deleteBoardPort = deleteBoardPort;
        this.getBoardPort = getBoardPort;
    }

    @Override
    public ArrayList<Board> getAll() {
        return listBoardPort.getBoards();
    }

    @Transactional
    @Override
    public Board create(Board board) {
        return createBoardPort.create(board);
    }

    @Transactional
    @Override
    public Board update(Board board) throws Exception {
        Board original = get(board.getId());
        original.setSubject(board.getSubject());
        original.setDescription(board.getDescription());
        return updateBoardPort.update(original);
    }

    @Transactional
    @Override
    public void delete(Board board) throws Exception {
        Board original = get(board.getId());
        deleteBoardPort.delete(original);
    }

    @Override
    public Board get(Long id) throws Exception {
        return getBoardPort.getBoardById(id);
    }

    
}
