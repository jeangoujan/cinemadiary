import { useCallback, useEffect, useState } from 'react'
import './MovieDetails.css'

interface Photo {
  id: number
  fileName: string
  url: string
  contentType: string
  fileSize: number
}

interface MovieDetail {
  id: number
  movieName: string
  watchDate: string
  generalRating: number
  plotRating: number
  actingRating: number
  atmosphereRating: number
  soundtrackRating: number
  emotionalRating: number
  review: string
  photos: Photo[]
}

interface MovieDetailsProps {
  movieId: number
  onBack: () => void
}

const ratingFields: { key: keyof MovieDetail; label: string }[] = [
  { key: 'generalRating', label: 'General' },
  { key: 'plotRating', label: 'Plot' },
  { key: 'actingRating', label: 'Acting' },
  { key: 'atmosphereRating', label: 'Atmosphere' },
  { key: 'soundtrackRating', label: 'Soundtrack' },
  { key: 'emotionalRating', label: 'Emotional' },
]

export function MovieDetails({ movieId, onBack }: MovieDetailsProps) {
  const [movie, setMovie] = useState<MovieDetail | null>(null)
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState<string | null>(null)
  const [selectedFile, setSelectedFile] = useState<File | null>(null)
  const [fileInputKey, setFileInputKey] = useState(0)
  const [uploading, setUploading] = useState(false)
  const [uploadSuccess, setUploadSuccess] = useState<string | null>(null)
  const [uploadError, setUploadError] = useState<string | null>(null)
  const [deletingPhotoId, setDeletingPhotoId] = useState<number | null>(null)
  const [deleteError, setDeleteError] = useState<string | null>(null)

  const fetchMovie = useCallback(async () => {
    const response = await fetch(
      `http://localhost:8080/api/movies/${movieId}`,
    )
    if (!response.ok) {
      throw new Error(`Request failed (${response.status})`)
    }
    return response.json() as Promise<MovieDetail>
  }, [movieId])

  useEffect(() => {
    let cancelled = false

    async function loadMovie() {
      setLoading(true)
      setError(null)
      try {
        const data = await fetchMovie()
        if (!cancelled) {
          setMovie(data)
        }
      } catch (err) {
        if (!cancelled) {
          setError(
            err instanceof Error ? err.message : 'Failed to load movie',
          )
        }
      } finally {
        if (!cancelled) {
          setLoading(false)
        }
      }
    }

    loadMovie()
    return () => {
      cancelled = true
    }
  }, [fetchMovie])

  async function handleDeletePhoto(photoId: number) {
    if (!window.confirm('Delete this photo?')) return

    setDeletingPhotoId(photoId)
    setDeleteError(null)

    try {
      const response = await fetch(
        `http://localhost:8080/api/movies/${movieId}/photos/${photoId}`,
        { method: 'DELETE' },
      )
      if (!response.ok) {
        throw new Error(`Request failed (${response.status})`)
      }
      const data = await fetchMovie()
      setMovie(data)
    } catch (err) {
      setDeleteError(
        err instanceof Error ? err.message : 'Failed to delete photo',
      )
    } finally {
      setDeletingPhotoId(null)
    }
  }

  async function handleUpload() {
    if (!selectedFile) return

    setUploading(true)
    setUploadSuccess(null)
    setUploadError(null)

    const formData = new FormData()
    formData.append('file', selectedFile)

    try {
      const response = await fetch(
        `http://localhost:8080/api/movies/${movieId}/photos`,
        {
          method: 'POST',
          body: formData,
        },
      )
      if (!response.ok) {
        throw new Error(`Request failed (${response.status})`)
      }
      setSelectedFile(null)
      setFileInputKey((k) => k + 1)
      setUploadSuccess('Photo uploaded successfully.')
      const data = await fetchMovie()
      setMovie(data)
    } catch (err) {
      setUploadError(
        err instanceof Error ? err.message : 'Failed to upload photo',
      )
    } finally {
      setUploading(false)
    }
  }

  if (loading) {
    return <p className="movie-details-status">Loading movie…</p>
  }

  if (error) {
    return (
      <div className="movie-details">
        <p className="movie-details-status movie-details-error" role="alert">
          {error}
        </p>
        <button type="button" className="movie-details-back" onClick={onBack}>
          Back to list
        </button>
      </div>
    )
  }

  if (!movie) {
    return null
  }

  return (
    <section className="movie-details">
      <button type="button" className="movie-details-back" onClick={onBack}>
        Back to list
      </button>

      <h2 className="movie-details-title">{movie.movieName}</h2>

      <dl className="movie-details-fields">
        <div>
          <dt>Watch date</dt>
          <dd>{movie.watchDate}</dd>
        </div>
        {ratingFields.map(({ key, label }) => (
          <div key={key}>
            <dt>{label}</dt>
            <dd>{movie[key] as number}</dd>
          </div>
        ))}
      </dl>

      {movie.review && (
        <div className="movie-details-review">
          <h3>Review</h3>
          <p>{movie.review}</p>
        </div>
      )}

      <div className="movie-details-upload">
        <h3>Upload photo</h3>
        <input
          key={fileInputKey}
          type="file"
          accept="image/*"
          onChange={(e) => {
            setSelectedFile(e.target.files?.[0] ?? null)
            setUploadSuccess(null)
            setUploadError(null)
          }}
        />
        <button
          type="button"
          disabled={!selectedFile || uploading}
          onClick={handleUpload}
        >
          {uploading ? 'Uploading…' : 'Upload'}
        </button>
        {uploadSuccess && (
          <p className="movie-details-message movie-details-success" role="status">
            {uploadSuccess}
          </p>
        )}
        {uploadError && (
          <p className="movie-details-message movie-details-error" role="alert">
            {uploadError}
          </p>
        )}
      </div>

      {movie.photos.length > 0 && (
        <div className="movie-details-photos">
          <h3>Photos</h3>
          {deleteError && (
            <p className="movie-details-message movie-details-error" role="alert">
              {deleteError}
            </p>
          )}
          <ul>
            {movie.photos.map((photo) => (
              <li key={photo.id}>
                <img src={`http://localhost:8080${photo.url}`} alt={photo.fileName} />
                <span>{photo.fileName}</span>
                <button
                  type="button"
                  className="movie-details-delete-photo"
                  disabled={deletingPhotoId !== null}
                  onClick={() => handleDeletePhoto(photo.id)}
                >
                  {deletingPhotoId === photo.id ? 'Deleting…' : 'Delete'}
                </button>
              </li>
            ))}
          </ul>
        </div>
      )}
    </section>
  )
}
