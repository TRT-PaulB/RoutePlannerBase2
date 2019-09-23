import React, { Component } from "react";
import Like from "./like";
import Table from "./table";
import auth from "../services/authService";
import { Link } from "react-router-dom";

// INPUT INTERFACE
// columns: array
// sortColumn: object
// onSort: func

class MoviesTable extends Component {
  columns = [
    // note that this value will not change in the lifecycle of this component, so no need to put in state
    // title, liked and delete use content to supply react component to Table / TableBody
    {
      name: "title",
      label: "Title",
      content: movie => <Link to={`/movies/${movie._id}`}>{movie.title}</Link>
    },

    { name: "genre.name", label: "Genre" },
    { name: "numberInStock", label: "Stock" },
    { name: "dailyRentalRate", label: "Rate" },
    {
      name: "liked",
      label: "Liked",
      content: (
        movie // function takes a movie object and returns a react element
      ) => (
        <Like
          liked={movie.liked}
          onLike={() => this.props.onLike(movie)}
          movie={movie}
        />
      )
    }

    // use to provide a key on a blank header, ie for delete
  ];

  deleteColumn = {
    key: "delete",
    content: movie => (
      <button
        onClick={() => this.props.onDelete(movie)}
        className="btn btn-danger btn-sm"
        movie={movie}
      >
        Delete{" "}
      </button>
    )
  };

  constructor() {
    super(); // always call the constructor parent class

    // note user could also be passed down from props, but cleaner to get it this way...
    const user = auth.getCurentUser();
    if (user && user.isAdmin) this.columns.push(this.deleteColumn);
  }

  // use generic components where possible, and avoid having mixed layers of abstraction
  render() {
    const { movies, onSort, sortColumn } = this.props;

    return (
      <Table
        columns={this.columns}
        data={movies}
        onSort={onSort}
        sortColumn={sortColumn}
      />
    );
  }
}

export default MoviesTable;
