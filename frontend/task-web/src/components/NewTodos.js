import React, { useState } from "react";
import { connect } from "react-redux";
import { addTask } from "../redux/actions";

const NewTodos = (props) => {
  const [todo, setTodo] = useState("");
  const add = () => {
    if (todo === "") {
      alert("Debe ingresar una tarea");
      return;
    }

    if (todo.length < 2) {
      alert("Debe ingresar una tarea de al menos 2 caracteres");
      return;
    }

    if (todo.length > 30) {
      alert("Debe ingresar una tarea de máximo 30 caracteres");
      return;
    }

    props.addTodo({
      id: Math.floor(Math.random() * 1000),
      subject: todo,
      completed: false,
    });
    setTodo("");
  };

  const handleChange = (e) => {
    setTodo(e.target.value);
  };

  return (
    <div className="addTodos">
      <input
        type="text"
        id="newTodo"
        onChange={(e) => handleChange(e)}
        className="todo-input"
        value={todo}
        maxLength={30}
        minLength={2}
      />

      <button className="add-btn" onClick={() => add()}>
        Agregar
      </button>
    </div>
  );
};

const mapStateToProps = (state) => {
  return {
    todos: state.tasks,
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    addTodo: (obj) => dispatch(addTask(obj)),
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(NewTodos);
