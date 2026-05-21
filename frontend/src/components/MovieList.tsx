import './MovieList.css'

export interface Movie {
  id: number
  movieName: string
  watchDate: string
  generalRating: number
  review: string
}

interface MovieListProps {
  movies: Movie[]
  loading: boolean
  error: string | null
  onSelectMovie: (id: number) => void
}

export function MovieList({
  movies,
  loading,
  error,
  onSelectMovie,
}: MovieListProps) {
  if (loading) {
    return <p className="movie-list-status">Loading movies…</p>
  }

  if (error) {
    return (
      <p className="movie-list-status movie-list-error" role="alert">
        {error}
      </p>
    )
  }

  if (movies.length === 0) {
    return <p className="movie-list-status">No movies yet.</p>
  }

  return (
    <ul className="movie-list">
      {movies.map((movie) => (
        <li key={movie.id} className="movie-card">
          <h2 className="movie-card-title">{movie.movieName}</h2>
          <dl className="movie-card-details">
            <div>
              <dt>Watched</dt>
              <dd>{movie.watchDate}</dd>
            </div>
            <div>
              <dt>Rating</dt>
              <dd>{movie.generalRating}</dd>
            </div>
          </dl>
          {movie.review && (
            <p className="movie-card-review">{movie.review}</p>
          )}
          <button
            type="button"
            className="movie-card-details-btn"
            onClick={() => onSelectMovie(movie.id)}
          >
            Details
          </button>
        </li>
      ))}
    </ul>
  )
}
