# Python3 implementation of QuickSort


# Function to find the partition position
def partition(array, low, high):

	# Choose the rightmost element as pivot
	pivot = array[high]

	# Pointer for greater element
	i = low - 1

	# Traverse through all elements
	# compare each element with pivot
	for j in range(low, high):
		if array[j] <= pivot:

			# If element smaller than pivot is found
			# swap it with the greater element pointed by i
			i = i + 1

			# Swapping element at i with element at j
			(array[i], array[j]) = (array[j], array[i])

	# Swap the pivot element with
	# the greater element specified by i
	(array[i + 1], array[high]) = (array[high], array[i + 1])

	# Return the position from where partition is done
	return i + 1


# Function to perform quicksort
def quicksort(array, low, high):
	if low < high:

		# Find pivot element such that
		# element smaller than pivot are on the left
		# element greater than pivot are on the right
		pi = partition(array, low, high)

		# Recursive call on the left of pivot
		quicksort(array, low, pi - 1)

		# Recursive call on the right of pivot
		quicksort(array, pi + 1, high)


# Merges two subarrays of array[].
# First subarray is arr[left..mid]
# Second subarray is arr[mid+1..right]
def merge(array, left, mid, right):
	subArrayOne = mid - left + 1
	subArrayTwo = right - mid

	# Create temp arrays
	leftArray = [0] * subArrayOne
	rightArray = [0] * subArrayTwo

	# Copy data to temp arrays leftArray[] and rightArray[]
	for i in range(subArrayOne):
		leftArray[i] = array[left + i]
	for j in range(subArrayTwo):
		rightArray[j] = array[mid + 1 + j]

	indexOfSubArrayOne = 0  # Initial index of first sub-array
	indexOfSubArrayTwo = 0  # Initial index of second sub-array
	indexOfMergedArray = left  # Initial index of merged array

	# Merge the temp arrays back into array[left..right]
	while indexOfSubArrayOne < subArrayOne and indexOfSubArrayTwo < subArrayTwo:
		if leftArray[indexOfSubArrayOne] <= rightArray[indexOfSubArrayTwo]:
			array[indexOfMergedArray] = leftArray[indexOfSubArrayOne]
			indexOfSubArrayOne += 1
		else:
			array[indexOfMergedArray] = rightArray[indexOfSubArrayTwo]
			indexOfSubArrayTwo += 1
		indexOfMergedArray += 1

	# Copy the remaining elements of left[], if any
	while indexOfSubArrayOne < subArrayOne:
		array[indexOfMergedArray] = leftArray[indexOfSubArrayOne]
		indexOfSubArrayOne += 1
		indexOfMergedArray += 1

	# Copy the remaining elements of right[], if any
	while indexOfSubArrayTwo < subArrayTwo:
		array[indexOfMergedArray] = rightArray[indexOfSubArrayTwo]
		indexOfSubArrayTwo += 1
		indexOfMergedArray += 1

# begin is for left index and end is right index
# of the sub-array of arr to be sorted
def mergeSort(array, begin, end):
	if begin >= end:
		return

	mid = begin + (end - begin) // 2
	mergeSort(array, begin, mid)
	mergeSort(array, mid + 1, end)
	merge(array, begin, mid, end)

# Function to print an array
def printArray(array, size):
	for i in range(size):
		print(array[i], end=" ")
	print()


# Driver code
if __name__ == "__main__":
	import timeit
	from copy import deepcopy

	import random
	n = 10000

	arr = [random.randrange(0, 100) for i in range(500)]
	
	l = len(arr)
	st = timeit.default_timer()

	for i in range(n):
		quicksort(deepcopy(arr), 0, l-1)

	et = timeit.default_timer()

	print(et - st)

	st = timeit.default_timer()

	for i in range(n):
		mergeSort(deepcopy(arr), 0, l-1)

	et = timeit.default_timer()

	print(et - st)

