import React from "react";
import Joi from "joi";
import Form from "../common/form";
import { getMovie } from "../services/movieService";
import { saveMovie } from "../services/movieService";
import { getGenres } from "../services/genreService";

// const obj = { title: "hard coded data", body: "blar blar barl" };
// const { data: post } = await http.post(config.apiEndpoint, obj);

// const posts = [post, ...this.state.posts];
// this.setState({ posts });

class DisplayMovieForm extends Form {
  state = {
    data: { title: "", genreId: "", numberInStock: "", rate: "" },
    genres: [],
    errors: {}
  };

  schema = {
    _id: Joi.string(),
    title: Joi.string()
      .required()
      .label("Title"),
    genreId: Joi.string()
      .required()
      .label("Genre"),
    numberInStock: Joi.number()
      .required()
      .min(0)
      .max(100)
      .label("Number in Stock"),
    rate: Joi.number()
      .required()
      .min(0)
      .max(10)
      .label("Rate")
  };

  async componentDidMount() {
    await this.populateGenres();
    await this.populateMovie();
  }

  async populateGenres() {
    const { data: genres } = await getGenres();
    this.setState({ genres });
  }

  async populateMovie() {
    //WAS const movie = getMovie(movieId);
    try {
      const movieId = this.props.match.params.movieId; // coming in from the Route
      if (movieId === "new") return; //...continue only to populate4 the form

      const { data: movie } = await getMovie(movieId);
      this.setState({ data: this.getMovieMap(movie) });
    } catch (e) {
      if (e.response && e.response.status === 404)
        // note return keyword not necessary here...
        return this.props.history.replace("/not-found");
    }
  }

  // NEW const { data: movie } = await getMovie(movieId);

  // redirect if the movie coulod not be retrieved
  // notice too that the return is required to stop this method executing beyond this line
  //if (!movie) return this.props.history.replace("/not-found"); // remember replace deactivates the back button, push activates it

  // this.setState({ data: this.mapToViewModle(movie) });
  // this.setState({ movie });

  getMovieMap = movie => {
    return {
      _id: movie._id,
      title: movie.title,
      genreId: movie.genre._id,
      numberInStock: movie.numberInStock,
      rate: movie.dailyRentalRate
    };
  };

  doSubmit = async () => {
    await saveMovie(this.state.data);
    this.props.history.push("/movies");
  };

  render() {
    const { match, history } = this.props;

    return (
      <React.Fragment>
        <h1>Movie Form: {match.params.movieId}</h1>
        <form onSubmit={this.handleSubmit}>
          {this.renderInput("title", "Title", true)}
          {this.renderSelect("genreId", "Genre", false, this.state.genres)}
          {this.renderInput("numberInStock", "Number in Stock", false)}
          {this.renderInput("rate", "Rate", false)}
          {this.renderButton("Save")}
        </form>
      </React.Fragment>
    );
  }
}

export default DisplayMovieForm;
