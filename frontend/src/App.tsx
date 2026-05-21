import { useCallback, useEffect, useState } from 'react'
import { AddMovieForm } from './components/AddMovieForm'
import { MovieDetails } from './components/MovieDetails'
import { MovieList, type Movie } from './components/MovieList'
import './App.css'

const API_URL = 'http://localhost:8080/api/movies'

function App() {
  const [movies, setMovies] = useState<Movie[]>([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState<string | null>(null)
  const [selectedMovieId, setSelectedMovieId] = useState<number | null>(null)

  const fetchMovies = useCallback(async () => {
    try {
      const response = await fetch(API_URL)
      if (!response.ok) {
        throw new Error(`Request failed (${response.status})`)
      }
      const data: Movie[] = await response.json()
      setMovies(data)
      setError(null)
    } catch (err) {
      setError(err instanceof Error ? err.message : 'Failed to load movies')
    } finally {
      setLoading(false)
    }
  }, [])

  useEffect(() => {
    fetchMovies()
  }, [fetchMovies])

  return (
    <main className="app">
      <header className="app-header">
        <h1>CinemaDiary</h1>
        <p>Your personal movie journal</p>
      </header>
      {selectedMovieId !== null ? (
        <MovieDetails
          movieId={selectedMovieId}
          onBack={() => setSelectedMovieId(null)}
        />
      ) : (
        <>
          <AddMovieForm onMovieCreated={fetchMovies} />
          <MovieList
            movies={movies}
            loading={loading}
            error={error}
            onSelectMovie={setSelectedMovieId}
          />
        </>
      )}
    </main>
  )
}

export default App
