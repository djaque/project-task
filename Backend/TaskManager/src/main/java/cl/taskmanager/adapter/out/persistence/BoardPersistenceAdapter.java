package cl.taskmanager.adapter.out.persistence;

import java.util.ArrayList;

import cl.taskmanager.application.port.out.board.CreateBoardPort;
import cl.taskmanager.application.port.out.board.GetBoardPort;
import cl.taskmanager.application.port.out.board.UpdateBoardPort;
import cl.taskmanager.application.port.out.board.DeleteBoardPort;
import cl.taskmanager.application.port.out.board.ListBoardPort;

import cl.taskmanager.common.PersistenceAdapter;
import cl.taskmanager.domain.Board;

@PersistenceAdapter
public class BoardPersistenceAdapter implements CreateBoardPort, UpdateBoardPort, DeleteBoardPort, GetBoardPort, ListBoardPort{
    
    private final SpringBoardRepository boardRepository;

    public BoardPersistenceAdapter(SpringBoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public ArrayList<Board> getBoards() {
        ArrayList<BoardEntity> boardsEntities = (ArrayList<BoardEntity>) boardRepository.findAll();
        ArrayList<Board> boardList = new ArrayList<Board>();
        for (BoardEntity board : boardsEntities) {
            boardList.add(BoardMapper.entityToDomain(board));
        }
        return boardList;
    }

    @Override
    public Board getBoardById(Long id) throws RuntimeException {
        BoardEntity boardOld = boardRepository.getReferenceById(id);
        if (boardOld == null) {
            throw new RuntimeException("ID not found");
        }
        return BoardMapper.entityToDomain(boardOld);
    }

    @Override
    public void delete(Board board) throws RuntimeException {
        boardRepository.deleteById(board.getId());
    }

    @Override
    public Board update(Board board) throws RuntimeException {
        return BoardMapper.entityToDomain(boardRepository.save(BoardMapper.domainToEntity(board)));
    }

    @Override
    public Board create(Board board) {
        return BoardMapper.entityToDomain(boardRepository.save(BoardMapper.domainToEntity(board))); 
    }
    
}
