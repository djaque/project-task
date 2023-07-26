import "./css/main.css";

import NewTodos from "./components/NewTodos";
import DisplayTodos from "./components/DisplayTodos";
import Header from "./components/Header";
function App() {
  
  return (
    <div className="App">
      <Header />
      <NewTodos />
      <DisplayTodos />
    </div>
  );
}

export default App;
