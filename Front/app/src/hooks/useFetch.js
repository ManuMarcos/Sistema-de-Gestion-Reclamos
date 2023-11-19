import { useEffect, useState } from "react"



export const useFetch = ( url, {method, headers, body} = {}) => {

    const[data, setData] = useState(null);
    const[isPending, setIsPending] = useState(false);
    const[error, setError] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
          setIsPending(true);
          try {
            const response = await fetch(url,{
                method: method,
                headers: {
                    Authorization: "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcwMDMzNDQxMCwiZXhwIjoxNzAwMzQwNDEwfQ.e95lz8EXnH4DZRrdkxn0iXhHPCcaW06MkUE-YFayors"
                },  
                body: JSON.stringify(body)
            });
            if (!response.ok) throw new Error(response.statusText);
            const json = await response.json();
            setIsPending(false);
            setData(json);
            setError(null);
          } catch (error) {
            setError(`${error} Could not Fetch Data `);
            console.log(error   )
            setIsPending(false);
         }
        };
        fetchData();
      }, [url]);
      return { data, isPending, error };
}