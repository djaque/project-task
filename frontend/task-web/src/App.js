import "./css/main.css";

import NewTodos from "./components/NewTodos";
import DisplayTodos from "./components/DisplayTodos";

function App() {
  
  return (
    <div className="App">
      <h1>Tareas</h1>
      <DisplayTodos />
      <NewTodos />
    </div>
  );
}

export default App;
