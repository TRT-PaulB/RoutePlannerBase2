import React, { Component } from "react";
import Pagination from "../common/pagination";
import { paginate } from "../utils/pageSplicer";
import ListGroup from "../common/listgroup";
import MoviesTable from "../common/moviesTable";
import _ from "lodash"; // used for sorting
import { Link } from "react-router-dom";
import { getMovies, deleteMovie } from "../services/movieService";
import { getGenres } from "../services/genreService";
import SearchBox from "./searchBox";
import { toast } from "react-toastify";

class Movies extends Component {
  state = {
    movies: [],
    genres: [],
    numItemsPerPage: 4,
    currentPage: 1,
    sortColumn: { column: "title", order: "asc" },
    searchQuery: "",
    selectedGenre: null
  };

  async componentDidMount() {
    // get the data destructured from the response object promise
    const { data } = await getGenres();
    //const genres = [{ _id: "", name: "All Genres" }, ...getGenres()];
    const genres = [{ _id: "", name: "All Genres" }, ...data];

    const { data: movies } = await getMovies();

    this.setState({ movies, genres }); // ie {genres: genres}
  }

  handleDelete = async movie => {
    // could apply an optimistic update here:
    const origMovies = this.state.movies;

    const movies = origMovies.filter(mov => mov._id !== movie._id);
    this.setState({ movies });

    try {
      await deleteMovie(movie._id);
    } catch (e) {
      if (e.response && e.response.status === 404) {
        toast.error("This movie has already been deleted");
      }
      this.setState({ movies: origMovies });
    }
  };

  handleLiked = movie => {
    const movies = [...this.state.movies];
    const index = movies.indexOf(movie);
    movies[index] = { ...movies[index] };
    movies[index].liked = !movies[index].liked;
    this.setState({ movies });
  };

  handlePageChange = page => {
    this.setState({ currentPage: page });
  };

  // note: if the event came direct from built in select component,
  //       the param would be e.  So:  const searchVal = e.currentTarget.value;
  //       -> but we extract this in the Input wrapper, and then pass it up as a query parameter
  handleSearch = query => {
    console.log("handling asearch OK");
    // disable filtering on (selectedGenre) if we are filtering on search (show page 1 of results)
    this.setState({ selectedGenre: null, currentPage: 1, searchQuery: query });
    // the query field (ie the search box value) is set in state to the searchQuery
  };

  // if filtering on the genre list, empty the search box field (do ot allow filtering on both)
  handleItemSelect = genre => {
    this.setState({ selectedGenre: genre, currentPage: 1, searchQuery: "" });
    // note this is a controlled component, so the searchQuery is set to an empty string, not to null
  };

  handleSorting = sortColumn => {
    this.setState({ sortColumn });
  };

  filterMovies = () => {
    const { movies, selectedGenre, searchQuery } = this.state;

    let filteredMovies = movies;
    if (searchQuery) {
      // NOTE searchQuery presumably returns false here if it is an empty string...
      filteredMovies = movies.filter(m =>
        m.title.toLowerCase().startsWith(searchQuery.toLowerCase())
      );
    } else if (selectedGenre && selectedGenre._id) {
      filteredMovies = movies.filter(
        movie => movie.genre._id === selectedGenre._id
      );
    }
    return filteredMovies;
  };

  getPagedData = () => {
    const {
      movies,
      currentPage,
      numItemsPerPage,
      selectedGenre,
      sortColumn,
      searchQuery
    } = this.state;

    let filteredMovies = this.filterMovies(searchQuery, selectedGenre);

    const sortedMovies = _.orderBy(
      filteredMovies,
      [sortColumn.column],
      [sortColumn.order]
    );

    const pagedMovies = paginate(sortedMovies, currentPage, numItemsPerPage);

    return { totalCount: filteredMovies.length, data: pagedMovies };
  };

  render() {
    const { length: moviesCount } = this.state.movies;
    const {
      sortColumn,
      selectedGenre,
      currentPage,
      numItemsPerPage,
      genres,
      searchQuery
    } = this.state;

    const { user } = this.props;

    const { data: movies, totalCount } = this.getPagedData();

    if (moviesCount === 0) return <p>No movies in the database</p>;

    return (
      <div className="row">
        <div className="col-2">
          <ListGroup
            items={genres}
            textProperty="name"
            valueProperty="_id"
            onItemSelect={this.handleItemSelect}
            selectedItem={selectedGenre}
          />
        </div>

        <div className="col">
          {user && (
            <Link
              to="/movies/new"
              className="btn btn-primary"
              style={{ marginBottom: 20 }}
            >
              Create New Movie
            </Link>
          )}
          <p>Showing {totalCount} movies in the database</p>

          <SearchBox value={searchQuery} onChange={this.handleSearch} />

          <MoviesTable
            movies={movies}
            onLike={this.handleLiked}
            onDelete={this.handleDelete}
            onSort={this.handleSorting}
            sortColumn={sortColumn}
          />
          <Pagination
            currentPage={currentPage}
            totalItems={totalCount}
            numItemsPerPage={numItemsPerPage}
            onPageChange={this.handlePageChange}
          />
        </div>
      </div>
    );
  }
}

/* 
ALTERNATIVE IS A BUTTON:

<button
            className="btn btn-primary"
            onClick={() => this.props.history.push("/movies/new")}
            style={{ marginBottom: 20 }}
          >
            Create New Movie
          </button>
*/

export default Movies;
