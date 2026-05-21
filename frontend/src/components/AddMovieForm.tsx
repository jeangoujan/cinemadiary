import { useState, type FormEvent } from 'react'
import './AddMovieForm.css'

const API_URL = 'http://localhost:8080/api/movies'

interface FormState {
  movieName: string
  watchDate: string
  generalRating: string
  plotRating: string
  actingRating: string
  atmosphereRating: string
  soundtrackRating: string
  emotionalRating: string
  review: string
}

const emptyForm: FormState = {
  movieName: '',
  watchDate: '',
  generalRating: '',
  plotRating: '',
  actingRating: '',
  atmosphereRating: '',
  soundtrackRating: '',
  emotionalRating: '',
  review: '',
}

const ratingFields: { name: keyof FormState; label: string }[] = [
  { name: 'generalRating', label: 'General' },
  { name: 'plotRating', label: 'Plot' },
  { name: 'actingRating', label: 'Acting' },
  { name: 'atmosphereRating', label: 'Atmosphere' },
  { name: 'soundtrackRating', label: 'Soundtrack' },
  { name: 'emotionalRating', label: 'Emotional' },
]

interface AddMovieFormProps {
  onMovieCreated: () => void
}

export function AddMovieForm({ onMovieCreated }: AddMovieFormProps) {
  const [form, setForm] = useState<FormState>(emptyForm)
  const [submitting, setSubmitting] = useState(false)
  const [success, setSuccess] = useState<string | null>(null)
  const [error, setError] = useState<string | null>(null)

  function updateField(name: keyof FormState, value: string) {
    setForm((prev) => ({ ...prev, [name]: value }))
    setSuccess(null)
    setError(null)
  }

  async function handleSubmit(event: FormEvent) {
    event.preventDefault()
    setSubmitting(true)
    setSuccess(null)
    setError(null)

    const body = {
      movieName: form.movieName.trim(),
      watchDate: form.watchDate,
      generalRating: Number(form.generalRating),
      plotRating: Number(form.plotRating),
      actingRating: Number(form.actingRating),
      atmosphereRating: Number(form.atmosphereRating),
      soundtrackRating: Number(form.soundtrackRating),
      emotionalRating: Number(form.emotionalRating),
      review: form.review.trim(),
    }

    try {
      const response = await fetch(API_URL, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(body),
      })
      if (!response.ok) {
        throw new Error(`Request failed (${response.status})`)
      }
      setForm(emptyForm)
      setSuccess('Movie added successfully.')
      onMovieCreated()
    } catch (err) {
      setError(err instanceof Error ? err.message : 'Failed to add movie')
    } finally {
      setSubmitting(false)
    }
  }

  return (
    <section className="add-movie-form">
      <h2>Add a movie</h2>
      <form onSubmit={handleSubmit}>
        <label className="form-field">
          <span>Movie name</span>
          <input
            type="text"
            value={form.movieName}
            onChange={(e) => updateField('movieName', e.target.value)}
            required
          />
        </label>

        <label className="form-field">
          <span>Watch date</span>
          <input
            type="date"
            value={form.watchDate}
            onChange={(e) => updateField('watchDate', e.target.value)}
            required
          />
        </label>

        <fieldset className="form-ratings">
          <legend>Ratings (1–10)</legend>
          <div className="form-ratings-grid">
            {ratingFields.map(({ name, label }) => (
              <label key={name} className="form-field">
                <span>{label}</span>
                <input
                  type="number"
                  min={1}
                  max={10}
                  step={1}
                  value={form[name]}
                  onChange={(e) => updateField(name, e.target.value)}
                  required
                />
              </label>
            ))}
          </div>
        </fieldset>

        <label className="form-field">
          <span>Review</span>
          <textarea
            value={form.review}
            onChange={(e) => updateField('review', e.target.value)}
            rows={4}
          />
        </label>

        <button type="submit" disabled={submitting}>
          {submitting ? 'Adding…' : 'Add movie'}
        </button>
      </form>

      {success && (
        <p className="form-message form-success" role="status">
          {success}
        </p>
      )}
      {error && (
        <p className="form-message form-error" role="alert">
          {error}
        </p>
      )}
    </section>
  )
}
